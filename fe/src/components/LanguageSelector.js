import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../services/helpers/ScreenHelper';

class LanguageSelector extends Component {

  changeLang = (e) => {
    console.log(this.props.match);
    console.log(this.props.history);
    this.props.history.push({
      "locale": e.currentTarget.id
    });
  }

  render() {
    return (
      <ul>
        <li><a id="en" onClick={this.changeLang}>English</a></li>
        <li><a id="zh" onClick={this.changeLang}>Chinese</a></li>
      </ul>
    )
  }
}


export default withRouter(LanguageSelector);
