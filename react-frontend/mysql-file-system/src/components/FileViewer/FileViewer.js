import './FileViewer.css';
import { Card } from "@mui/material";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";

const FileViewer = (props) => {
  return <Card className="FileViewer">
    <Typography className="FileNameDisplay">
      {props.displayFile ? props.displayFile.name : "No File Selected"}
    </Typography>
    <Paper className="surface" variant="outlined">
      { props.displayFile ?
          <Typography>{props.displayFile.contents}</Typography> :
          <div className="NoFileFoundDisplay">
            <Typography>Select a file to view its contents here.</Typography>
          </div>
      }
    </Paper>
  </Card>
}

export default FileViewer;