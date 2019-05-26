import React from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../../services/helpers/functionHelper';

  const changeLang = (e, props, history, location, match) => {
    e.preventDefault();
    const { pathname }  = location;
    const { locale }    = match.params;
    const newPathName   = pathname.replace(locale, e.currentTarget.id );
    history.push(newPathName);
  }

  export const LanguageSelector = withRouter(({history, location, match, ...props}) => {
    return (
      <ul>
        <li><a id="en-GB" onClick={(e) => changeLang(e, props, history, location, match)}>English</a></li>
        <li><a id="zh-HK" onClick={(e) => changeLang(e, props, history, location, match)}>Chinese</a></li>
      </ul>
    );
  });
