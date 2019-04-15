import React from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../../services/helpers/Helper';

class LanguageSelector extends Component {

  changeCurr = (e, props) => {
    const url = props.location.pathname;
    const search = props.location.search;
    const { currency } = props.match.params;
    props.history.push(url.replace(currency || '', event.target.id) + search);
  }

  export const LanguageSelector = withRouter((history, ...props) => {
    return (
      <ul>
        <li><a id="USD" onClick={this.changeCurr}>USD</a></li>
        <li><a id="HKD" onClick={this.changeCurr}>HKD</a></li>
      </ul>
    )
  });
