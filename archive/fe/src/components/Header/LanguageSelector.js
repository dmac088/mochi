import React from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../../services/helpers/functionHelper';

  const changeLang = (e, props, history) => {
    history.push('/' + e.currentTarget.id + '/HKD');
  }

  export const LanguageSelector = withRouter(({history, ...props}) => {
    return (
      <ul>
        <li><a id="en-GB" onClick={(e) => changeLang(e, props, history)}>English</a></li>
        <li><a id="zh-HK" onClick={(e) => changeLang(e, props, history)}>Chinese</a></li>
      </ul>
    );
  });
