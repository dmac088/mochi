import React from 'react';
import { Link } from  'react-router-dom';
import { DropdownButton, MenuItem  } from 'react-bootstrap';

const PageSize = (props) => {
  return (
    <DropdownButton
        id="1"
        title="test"
      >
       <MenuItem id="5" onClick={props.changePageSize} eventKey="5">5</MenuItem>
       <MenuItem id="10" onClick={props.changePageSize} eventKey="10">10</MenuItem>
       <MenuItem id="20" onClick={props.changePageSize} eventKey="20" active>
         20
       </MenuItem>
    </DropdownButton>
  )
};

export default PageSize;
