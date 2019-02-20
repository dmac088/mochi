import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../services/helpers/ScreenHelper';

class LanguageSelector extends Component {

  changeLang = (e) => {
    const url = this.props.location.pathname;
    const { locale } = this.props.match.params;
    console.log(url);
    console.log(locale);
    this.props.history.push(url.replace(locale, event.target.id));
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
