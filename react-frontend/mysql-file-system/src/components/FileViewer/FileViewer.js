import './FileViewer.css';
import { Card } from "@mui/material";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import {useNavigate, useParams} from "react-router-dom";
const api = require("../../utilities/ApiUtils");

const FileViewer = (props) => {
  const navigate = useNavigate();
  const params = useParams();
  const handleClick = () => {
    api.deleteFile(props.displayFile.id)
        .then(() => {
          sessionStorage.removeItem("shadowTree");
          const names = `/${params["*"]}`.split("/");
          props.reset();
          if (names[names.length - 1] === props.displayFile.name) {
              navigate(-1);
          } else {
              navigate(0);
          }
        })
  }

  return <Card className="FileViewer">
    <Typography className="FileNameDisplay">
      {props.displayFile ? props.displayFile.name : "No File Selected"}
    </Typography>
    <Paper className="surface" variant="outlined">
      { props.displayFile ?
          <Typography>{props.displayFile.content}</Typography> :
          <div className="NoFileFoundDisplay">
            <Typography>Select a file to view its contents here.</Typography>
          </div>
      }
    </Paper>
    {props.displayFile ?
      <div className="deleteBar">
        <Button onClick={handleClick}
                sx={{ padding: '10px' }}
                color="error">
          Delete File
        </Button>
      </div> : <></>}
  </Card>
}

export default FileViewer;