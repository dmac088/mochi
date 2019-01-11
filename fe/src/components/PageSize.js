import React from 'react';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem, Badge, DropdownButton, MenuItem } from 'react-bootstrap';

const PageSize = (props) => {
    console.log(props.currentPageSize);
    return (
      <DropdownButton>
        <MenuItem id="5" onClick={props.changePageSize}>Action</MenuItem>
        <MenuItem id="10" onClick={props.changePageSize}>Another action</MenuItem>
      </DropdownButton>
    );
}

export default PageSize;
