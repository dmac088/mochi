import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../../services/helpers/ScreenHelper';

class LanguageSelector extends Component {

  changeLang = (e) => {
    const url = this.props.location.pathname;
    const search = this.props.location.search;
    const { locale } = this.props.match.params;
    this.props.history.push(url.replace(locale || '', event.target.id) + search);
  }

  render() {
    return (
      <ul>
        <li><a id="en-GB" onClick={this.changeLang}>English</a></li>
        <li><a id="zh-HK" onClick={this.changeLang}>Chinese</a></li>
      </ul>
    )
  }
}


export default withRouter(LanguageSelector);
