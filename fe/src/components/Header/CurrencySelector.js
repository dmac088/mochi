import React from 'react';
import { withRouter } from "react-router-dom";
import { updateParams } from '../../services/helpers/functionHelper';

  const changeCurr = (e, props, routeProps) => {
    const url = routeProps.location.pathname;
    const search = routeProps.location.search;
    const { currency } = routeProps.match.params;
    routeProps.history.push(url.replace(currency || '', e.target.id) + search);
  }

  export const CurrencySelector = withRouter(({history, location, match, ...props}) => {
    return (
      <ul>
        <li><a id="USD" onClick={(e) => changeCurr(e, props, {history, location, match})}>USD</a></li>
        <li><a id="HKD" onClick={(e) => changeCurr(e, props, {history, location, match})}>HKD</a></li>
      </ul>
    );
  });
