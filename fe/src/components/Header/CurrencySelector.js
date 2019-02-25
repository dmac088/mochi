import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../../services/helpers/Helper';

class LanguageSelector extends Component {

  changeCurr = (e) => {
    const url = this.props.location.pathname;
    const search = this.props.location.search;
    const { currency } = this.props.match.params;
    this.props.history.push(url.replace(currency || '', event.target.id) + search);
  }

  render() {
    return (

      <ul>
        <li><a id="USD" onClick={this.changeCurr}>USD</a></li>
        <li><a id="HKD" onClick={this.changeCurr}>HKD</a></li>
      </ul>
    )
  }
}


export default withRouter(LanguageSelector);
