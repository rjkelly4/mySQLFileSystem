import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import Paper from "@mui/material/Paper";

import "./FileButton.css";

const FileButton = (props) => {
  const [fullPath, setFullPath] = useState("");
  const navigate = useNavigate();

  const getActiveObject = (id) => {
    return props.activePath.find(file => file.id === id);
  }
  const buildLinkPath = () => {
    let path = "";
    let runner = props.file;
    while(runner.name !== "/") {
      console.log(runner);
      path = runner.name + path;
      console.log(path);
      runner = getActiveObject(runner.parent);
    }
    path = "/browse/" + path;
    console.log(path);
    return path;
  }

  useEffect(() => {
    console.log(props.activePath);
    setFullPath(buildLinkPath());
  }, [props.activePath]);

  const handleClick = () => {
    navigate(fullPath);
  }

  return (
      <Paper onClick={handleClick}
             elevation={props.file.active ? 3 : 0}
             sx={{ padding: '10px' }}>
        {props.file.name}
      </Paper>
  )
}

export default FileButton;