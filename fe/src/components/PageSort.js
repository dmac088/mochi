import React from 'react';
import { DropdownButton, MenuItem  } from 'react-bootstrap';

const sortAliases = {
  1:"name (desc)",
  2:"price (desc)",
}

const PageSort = (props) => {
  return (
    <DropdownButton
        id="1"
        title={"Sort: " + sortAliases[props.currentPageSort]}
      >
       <MenuItem id="1" onClick={props.changePageSort} eventKey="1" active={props.currentPageSort === 1}>{sortAliases[1]}</MenuItem>
       <MenuItem id="2" onClick={props.changePageSort} eventKey="2" active={props.currentPageSort === 2}>{sortAliases[2]}</MenuItem>
    </DropdownButton>
  )
};

export default PageSort;
