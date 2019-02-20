import React, { Component } from 'react';
import { NavDropdown, MenuItem } from 'react-bootstrap';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class LanguageSelector extends Component {

  changeLang = (event) => {
    let urlParams = (qs.parse(this.props.history.location.search));
    let mergedParams = Object.assign(urlParams, { lang: event.target.id });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }

  render() {
    return (
      <NavDropdown eventKey={3} title={this.props.lang} id="basic-nav-dropdown">
        <MenuItem id="en-GB" onClick={this.changeLang}>
            ENG
        </MenuItem>
        <MenuItem id="zh-HK" onClick={this.changeLang}>
            HKG
        </MenuItem>
      </NavDropdown>
    )
  }
}


export default withRouter(LanguageSelector);
