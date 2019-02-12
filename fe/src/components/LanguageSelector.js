import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../services/helpers/ScreenHelper';
import qs from 'query-string';

class LanguageSelector extends Component {

  changeLang = (event) => {
    updateParams(this.props.history.location.search,
                { lang: event.target.id },
                  this.props.history);
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
