import React from 'react';
import { DropdownButton, MenuItem  } from 'react-bootstrap';

const PageSize = (props) => {
  return (
    <DropdownButton
        id="pageSize"
        title={"Page Size (" + props.currentPageSize + ")"}
      >
       <MenuItem id="5" onClick={props.changePageSize} eventKey="5" active={props.currentPageSize === "5"}>5</MenuItem>
       <MenuItem id="10" onClick={props.changePageSize} eventKey="10" active={props.currentPageSize === "10"}>10</MenuItem>
       <MenuItem id="20" onClick={props.changePageSize} eventKey="20" active={props.currentPageSize === "20"}>20</MenuItem>
    </DropdownButton>
  )
};

export default PageSize;
