import { Paper, Modal, Typography, TextField, Button } from "@mui/material";
import { useState } from "react";

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  minWidth: 600,
  padding: 4,
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'space-between',
};

const NewFileModal = (props) => {
  const [nameInput, setNameInput] = useState("");
  const [contentInput, setContentInput] = useState("");

  const getNameHelperText = () => {
    if (!nameInput) {
      return "Please enter a name";
    } else if (nameInput.includes("/")) {
      return "The name cannot include a forward slash";
    } else {
      return " ";
    }
  }

  const handleInputChange = (e) => {
    setNameInput(e.target.value);
  }
  
  const handleContentChange = (e) => {
    setContentInput(e.target.value);
  }

  const handleConfirm = (e) => {
    alert(`${nameInput} was created in ${props.path}!`);
    setNameInput("");
    setContentInput("");
    props.close();
  }

  const handleCancel = (e) => {
    setNameInput("");
    setContentInput("");
    props.close();
  }

  return <Modal open={props.open} onClose={handleCancel} >
    <Paper sx={style}>
      <Typography sx={{paddingBottom: 2}}>{"Create a new file in:"}</Typography>
      <Typography sx={{paddingBottom: 2}}>{props.path}</Typography>
      <TextField label="Name"
                 variant="outlined"
                 value={nameInput}
                 onChange={handleInputChange}
                 autoComplete="new-password"
                 helperText={getNameHelperText()}
                 sx={{paddingBottom: 2}} />
      <TextField label="contents"
                 variant="outlined"
                 value={contentInput}
                 onChange={handleContentChange}
                 autoComplete="new-password"
                 helperText={!contentInput ? "Please enter the file contents" : " "}
                 multiline
                 maxRows={10}
                 sx={{paddingBottom: 2}}/>
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

export default NewFileModal;