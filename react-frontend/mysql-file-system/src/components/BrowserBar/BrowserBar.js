import { Button, InputAdornment, TextField } from "@mui/material";

import FolderOpenIcon from '@mui/icons-material/FolderOpen';
import DriveFileMoveIcon from '@mui/icons-material/DriveFileMove';

import './BrowserBar.css'

const BrowserBar = (props) => {
  return (
    <div className={"container"}>
      <form className="barContainer" onSubmit={props.handleSubmit}>
        <TextField label="Where would you like to look?"
                   value={props.pathInput}
                   onChange={props.handlePathInputChange}
                   variant="outlined"
                   margin="normal"
                   InputProps={{
                     startAdornment: (
                       <InputAdornment position="start">
                         <FolderOpenIcon />
                       </InputAdornment>
                     )
                    }}
                   fullWidth/>
      </form>
      <div className="buttonContainer">
        <Button className={"submitButton"}
                variant="contained"
                onClick={props.handleSubmit}>
          <DriveFileMoveIcon />
        </Button>
      </div>
    </div>
  )
}

export default BrowserBar;