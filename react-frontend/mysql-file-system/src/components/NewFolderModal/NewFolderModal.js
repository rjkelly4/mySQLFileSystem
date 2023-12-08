import { Paper, Modal, Typography, TextField, Button } from "@mui/material";
import { useState } from "react";
const api = require("../../utilities/ApiUtils");

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  minWidth: 400,
  padding: 4,
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'space-between',
};

const NewFolderModal = (props) => {
  const [nameInput, setNameInput] = useState("");

  const getHelperText = () => {
    if (!nameInput) {
      return "Please enter a name";
    } else if (nameInput.includes("/")) {
      return "The name cannot include a forward slash";
    } else {
      return " ";
    }
  }

  const handleInputChange = (e) => {
    const newInput = e.target.value;
    setNameInput(newInput);
  }

  const handleConfirm = (e) => {
    api.postDirectory(nameInput, props.parent)
        .then(() => {
          setNameInput("");
          props.close();
          props.refresh();
        })
  }

  const handleCancel = (e) => {
    setNameInput("");
    props.close();
  }

  return <Modal open={props.open} onClose={handleCancel} >
    <Paper sx={style}>
      <Typography sx={{paddingBottom: 2}}>{"Create a new folder in:"}</Typography>
      <Typography sx={{paddingBottom: 2}}>{props.path === "//" ? "/" : props.path}</Typography>
      <TextField label="Name"
                 variant="outlined"
                 value={nameInput}
                 onChange={handleInputChange}
                 autoComplete="new-password"
                 helperText={getHelperText()}
                 sx={{paddingBottom: 2}} />
      <div style={{display: "flex", justifyContent: "space-around"}}>
        <Button variant="outlined" onClick={handleCancel}>Cancel</Button>
        <Button variant="contained"
                onClick={handleConfirm} disabled={!nameInput || nameInput.includes("/")}>
          Confirm
        </Button>
      </div>
    </Paper>
  </Modal>
}

export default NewFolderModal;