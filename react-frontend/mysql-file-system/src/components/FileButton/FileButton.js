import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import Paper from "@mui/material/Paper";

import "./FileButton.css";

const FileButton = (props) => {
  const navigate = useNavigate();

  const handleClick = () => {
    // console.log(props.path);
    navigate("/browse" + props.path);
  }

  return (
      <Paper onClick={handleClick}
             elevation={props.file.isActive ? 3 : 0}
             sx={{ padding: '10px' }}>
        {props.file.name + (props.file.children ? "/" : "")}
      </Paper>
  )
}

export default FileButton;