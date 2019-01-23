import React from 'react';
import { NavDropdown, MenuItem } from 'react-bootstrap';

const LanguageSelector = (props) => {
  return (
    <NavDropdown eventKey={3} title={props.lang} id="basic-nav-dropdown">
      <MenuItem id="ENG" onClick={props.changeLang}>
          ENG
      </MenuItem>
      <MenuItem id="HKG" onClick={props.changeLang}>
          HKG
      </MenuItem>
    </NavDropdown>
  );
}

export default LanguageSelector;
