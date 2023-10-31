import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";

import BrowserBar from "../BrowserBar/BrowserBar";
import TreeViewer from "../TreeViewer/TreeViewer";
import { Divider } from "@mui/material";

import "./BrowserPage.css";

import { testFileTree } from "../../data/testFileTree";


const BrowserPage = (props) => {

  const navigate = useNavigate();
  const params = useParams();
  const [fileColumns, setFileColumns] = useState([]);
  const [activePath, setActivePath] = useState([]);
  const [validPath, setValidPath] = useState(true);
  const [pathInput, setPathInput] = useState("");

  const fetchRelevantFiles = () => {
    const path = `/${params["*"]}`
    setPathInput(path);
    // TODO: This fileNames matching only works for directories because of the appended '/' at the end...
    const fileNames = path.split("/").map((fileName) => `${fileName}/`);

    let parentId = 0;
    let columns = [];
    for (let i = 0; i < fileNames.length; i++) {
      // Find all files with the matching parentId (replace with SQL query)
      const fauxFileTree = JSON.parse(JSON.stringify(testFileTree));
      const fileColumn = fauxFileTree.filter(file => { return file.parent === parentId});
      // Determine which file is the one requested and set it to "active"
      const activeFile = fileColumn.find(file => file.name === fileNames[i]);
      // There will be no match on the last iteration
      if (activeFile) {
        activeFile.active = true;
        // Set the new parentId for the next iteration
        parentId = activeFile.id;
      } else if (i < fileNames.length - 1) {
        setValidPath(false);
        return;
      }
      // Add this layer of file structure as a column
      columns.push(fileColumn);
    }

    setFileColumns(columns);
    const activeFiles = columns.map(column => column.find(file => file.active));
    setActivePath(activeFiles);
  }

  useEffect(fetchRelevantFiles, [params]);

  const handlePathInputChange = (event) => {
    setPathInput(event.target.value);
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    navigate(`/browse${pathInput}`);
    navigate(0);
  }

  // useEffect(() => {
  //   console.log(fileColumns);
  // }, [fileColumns])
  // useEffect(() => {
  //   console.log(objectPath);
  // }, [objectPath])

  return (
    <div className="BrowserPage" >
      <BrowserBar pathInput={pathInput}
                  handleSubmit={handleSubmit}
                  handlePathInputChange={handlePathInputChange} />
      <Divider orientation="horizontal" flexItem />
      <TreeViewer validPath={validPath}
                  fileColumns={fileColumns}
                  activePath={activePath}/>
    </div>
  )
}

export default BrowserPage;