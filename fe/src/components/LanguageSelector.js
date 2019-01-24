import React, { Component } from 'react';
import { NavDropdown, MenuItem } from 'react-bootstrap';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class LanguageSelector extends Component {
  
  changeLang = (event) => {
    //get the query parameters
    const query =  { lang: event.target.id };
    const searchString = qs.stringify(query);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }

  render() {
    return (
      <NavDropdown eventKey={3} title={this.props.lang} id="basic-nav-dropdown">
        <MenuItem id="ENG" onClick={this.changeLang}>
            ENG
        </MenuItem>
        <MenuItem id="HKG" onClick={this.changeLang}>
            HKG
        </MenuItem>
      </NavDropdown>
    )
  }
}


export default withRouter(LanguageSelector);
