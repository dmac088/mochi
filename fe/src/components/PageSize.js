import React from 'react';
import {Badge, DropdownButton, MenuItem } from 'react-bootstrap';

const PageSize = (props) => {
    console.log(props.currentPageSize);
    return (

      <DropdownButton
        title="test title"
      >
        <MenuItem id="5" onClick={props.changePageSize}>Action</MenuItem>
        <MenuItem id="10" onClick={props.changePageSize}>Another action</MenuItem>
      </DropdownButton>

    );
}

export default PageSize;
