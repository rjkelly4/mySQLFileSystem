import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";

import BrowserBar from "../BrowserBar/BrowserBar";
import TreeViewer from "../TreeViewer/TreeViewer";
import FileViewer from "../FileViewer/FileViewer";
import NewFolderModal from "../NewFolderModal/NewFolderModal";
import NewFileModal from "../NewFileModal/NewFileModal";
import { Divider, SpeedDial, SpeedDialAction, SpeedDialIcon } from "@mui/material";

import CreateNewFolderIcon from '@mui/icons-material/CreateNewFolder';
import NoteAddIcon from '@mui/icons-material/NoteAdd';

import "./BrowserPage.css";

import { testFileTree } from "../../data/testFileTree";

/*
 * Implementing Shadow Structure:
 *
 * Initialization:
 *  Check for empty entry, Initialize empty object in sessionStorage when found
 *  Query endpoint for batched structure of depth X starting at root
 *  Update marker for each directory indicating whether its children have been retrieved from endpoint
 *  Push structure into sessionStorage entry
 *
 * Use:
 *  Rather than making a request on each click, retrieve sessionStorage entry for use as database
 *  Compare provided path against shadow structure.
 *  If the path reaches a directory whose children or grandchildren have not been retrieved, update.
 *  Otherwise, use cached shadow structure to get relevant files for tree view.
 *
 * Updating:
 *  This should occur when an entry's grandchildren have not been retrieved to prevent slow user interaction
 *  While path is unfulfilled by shadow structure,
 *    Retrieve batch structure of depth X from endpoint starting at last directory represented in shadow structure
 *    Merge retrieved batch structure with existing sessionStorage entry
 *    Update sessionStorage entry with merged shadow structure
 */

const BrowserPage = (props) => {

  const navigate = useNavigate();
  const params = useParams();
  const [fileColumns, setFileColumns] = useState([]);
  const [activeFiles, setActiveFiles] = useState([]);
  const [validPath, setValidPath] = useState(true);
  const [pathInput, setPathInput] = useState("");
  const [displayFile, setDisplayFile] = useState(null);
  const [newFolderModalOpen, setNewFolderModalOpen] = useState(false);
  const [newFileModalOpen, setNewFileModalOpen] = useState(false);

  const requestData = (pathname, depth) => {
    const endpoint = `//127.0.0.1:8088/api/browse/folders?path=${pathname}`
    // const response = fetch(endpoint);
    // const data = response.json();
    //
    // console.log(`Response: ${data}`);

    fetch(endpoint)
      .then(response => response.json())
      .then(data => console.log(`Response: ${data}`));

    // Get a response from the endpoint using the pathname & depth
    const database = JSON.parse(JSON.stringify(testFileTree));
    // Update the current shadow tree
    let shadowTree = JSON.parse(sessionStorage.getItem("shadowTree"));
    shadowTree = {...shadowTree, ...database};
    sessionStorage.setItem("shadowTree", JSON.stringify(shadowTree));
  }

  const fetchRelevantFiles = () => {
    // Check for the shadowtree, if it doesn't exist initialize it.
    if (!sessionStorage.getItem("shadowTree")) {
      sessionStorage.setItem("shadowTree", JSON.stringify({}));
      requestData("/", 6);
    }

    // Get the path
    const path = `/${params["*"]}`;
    // console.log(path);
    setPathInput(path);
    const localTree = JSON.parse(sessionStorage.getItem("shadowTree"));
    const fileNames = path.split("/");
    fileNames.shift();
    // console.log(fileNames)

    setValidPath(true);

    const columns = [];

    let file = localTree;
    file.isActive = true;
    columns.push([file]);

    for (let fileName of fileNames) {
      // console.log(`File name: ${fileName}`)
      // Add the children to the display columns
      let children = file.children;
      columns.push(children);
      // If the last element of fileName is "", the path is to a directory
      if (!fileName) {
        break;
      }
      file = children.find((child) => child.name === fileName);
      if (!file) {
        setValidPath(false);
        return;
      }
      file.isActive = true;
    }

    setFileColumns(columns);
    // console.log(columns);
    const activeFileObjects = columns.map(column => column.find(file => file.isActive));
    // console.log("Active file objects:");
    // console.log(activeFileObjects);
    // If the last item is a file, update the displayFile by sending request to the endpoint.
    if (activeFileObjects[activeFileObjects.length - 1] &&
      activeFileObjects[activeFileObjects.length - 1].contents) {
      setDisplayFile(activeFileObjects[activeFileObjects.length - 1]);
    }
    setActiveFiles(activeFileObjects);

  }

  useEffect(fetchRelevantFiles, [params]);

  /**
   * Updates the pathInput state to reflect changes in the TextInput component of the
   * BrowserBar component using a controlled input design.
   *
   * @param event The onChange event associated with changes in the TextInput component
   */
  const handlePathInputChange = (event) => {
    setPathInput(event.target.value);
  }

  /**
   * Intended to handle submission of the BrowserBar's TextEntry or Button component.
   * Navigates to the pathInput represented in the TextEntry component of the BrowserBar
   * within the file browser.
   *
   * @param event the onSubmit event from the BrowserBar's TextEntry or Button component
   */
  const handlePathSubmit = (event) => {
    event.preventDefault();
    navigate(`/browse${pathInput}`);
  }

  const handleNewFolderModalOpen = (event) => {
    event.preventDefault();
    setNewFolderModalOpen(true);
  }

  const handleNewFolderModalClose = () => {
    setNewFolderModalOpen(false);
  }

  const handleNewFileModalOpen = (event) => {
    event.preventDefault();
    setNewFileModalOpen(true);
  }

  const handleNewFileModalClose = () => {
    setNewFileModalOpen(false);
  }

  /**
   * The JSX to be rendered when a BrowserPage is rendered.
   */
  return (
    <div className="BrowserPage" >
      <BrowserBar pathInput={pathInput}
                  handleSubmit={handlePathSubmit}
                  handlePathInputChange={handlePathInputChange} />
      <Divider orientation="horizontal" flexItem />
      <div className="ViewPort">
        <TreeViewer validPath={validPath}
                    fileColumns={fileColumns}
                    activeFiles={activeFiles}/>
        <FileViewer displayFile={displayFile} />
      </div>
      <SpeedDial ariaLabel="SpeedDial Add Item"
                 className="SpeedDialAdder"
                 direction="right"
                 icon={<SpeedDialIcon />}>
        <SpeedDialAction icon={<CreateNewFolderIcon />}
                         tooltipTitle="New Folder"
                         onClick={handleNewFolderModalOpen}/>
        <SpeedDialAction icon={<NoteAddIcon />}
                         tooltipTitle="New File"
                         onClick={handleNewFileModalOpen}/>
      </SpeedDial>
      <NewFolderModal open={newFolderModalOpen}
                      close={handleNewFolderModalClose}
                      path={`/${params["*"].split("/").slice(0, -1).join("/")}/`}/>
      <NewFileModal open={newFileModalOpen}
                    close={handleNewFileModalClose}
                    path={`/${params["*"].split("/").slice(0, -1).join("/")}/`}/>
    </div>
  )
}

export default BrowserPage;