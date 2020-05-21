import React from "react";
import { withRouter } from 'react-router-dom';
import { routeToPage } from '../../../../services/Routing/Helper';

export const Currency = withRouter(({...props}) => {
    const { params } = props.match;
    const { history } = props;

    return (
        <CurrencyBase   params={params}
                        history={history} />
    );
});

export const CurrencyBase = (props) => {
    const { params, history } = props;
    const { lang, curr } = params;

    const changeCurr = (e) => {
        e.preventDefault();
        routeToPage(history, {curr: e.currentTarget.id, lang: lang}, '');
    } 

    return (
        <li> <a id="currency" href="#">{curr}<i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="HKD" onClick={changeCurr}>HKD</a></li>
                <li><a href="#" id="USD" onClick={changeCurr}>USD</a></li>
            </ul>
        </li>
    );
};

export default Currency;
