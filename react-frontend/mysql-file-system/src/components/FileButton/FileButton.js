import { useNavigate } from "react-router-dom";
import Paper from "@mui/material/Paper";
import "./FileButton.css";

const FileButton = (props) => {
  const navigate = useNavigate();

  const handleClick = () => {
    if (!props.file.isDirectory) {

    }
    let navigatePath = "/browse" + props.path;
    if (props.file.isDirectory && props.file.name !== "") {
      navigatePath += "/";
    }
    navigate(navigatePath);
  }

  return (
      <Paper onClick={handleClick}
             elevation={props.file.isActive ? 3 : 0}
             sx={{ padding: '10px' }}>
        {props.file.name + (props.file.isDirectory ? "/" : "")}
      </Paper>
  )
}

export default FileButton;