import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class LanguageSelector extends Component {

  changeLang = (event) => {
    console.log("changeLang");
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
      <ul>
        <li><a id="ENG" onClick={this.changeLang}>English</a></li>
        <li><a id="HKG" onClick={this.changeLang}>Chinese</a></li>
      </ul>
    )
  }
}


export default withRouter(LanguageSelector);
