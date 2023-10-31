import './TreeViewer.css';
import { Divider, Stack } from "@mui/material";
import FileColumn from "../FileColumn/FileColumn";
import { useEffect, useRef } from "react";

const TreeViewer = (props) => {
  const endRef = useRef(null);

  // Scrolling behavior adapted from https://stackoverflow.com/a/52266212
  const scrollRight = () => {
    endRef.current?.scrollIntoView({behavior: "smooth"});
  }

  useEffect(() => {
    scrollRight();
  }, [props.fileColumns]);


  if (!props.validPath) {
    return <h1>Invalid Path!</h1>;
  } else {
    return (
      <Stack sx={{ overflowX: 'auto', flex: '1 1 auto' }}
             className="columnHolder"
             direction="row"
             spacing={1}
             divider={<Divider orientation="vertical" flexItem />}>
        {props.fileColumns.map((fileColumn, i) =>
          <FileColumn key={i} files={fileColumn} activePath={props.activePath} />
        )}
        <div ref={endRef} />
      </Stack>
    );
  }
}

export default TreeViewer;