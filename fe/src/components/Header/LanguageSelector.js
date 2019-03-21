import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../../services/helpers/Helper';

class LanguageSelector extends Component {

  changeLang = (e) => {
    this.props.history.push('/' + e.currentTarget.id + '/HKD');
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
