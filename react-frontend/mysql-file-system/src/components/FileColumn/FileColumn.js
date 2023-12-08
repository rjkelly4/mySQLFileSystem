import { Stack, Typography } from "@mui/material";

import FileButton from "../FileButton/FileButton";

import "./FileColumn.css";

const FileColumn = (props) => {
  if (props.files.length === 0) {
    return (
      <Typography sx={{textOrientation: 'sideways',
                       writingMode: 'vertical-lr',
                       paddingTop: 2,
                       color: 'darkgray'}}>
        There's nothing here...
      </Typography>
    );
  } else {
    return (
      <Stack className="column"
                  sx={{ padding: '10px', overflow: 'auto', flex: '0 0 auto' }}
                  spacing={1}>
        {props.files.map((file) =>
          <FileButton file={file}
                      path={props.parentPath + file.name + (file.children && file.name ? "/" : "")}
                      key={file.id + (file.isDirectory ? "D" : "F")}/>
        )}
      </Stack>
    )
  }
}

export default FileColumn;