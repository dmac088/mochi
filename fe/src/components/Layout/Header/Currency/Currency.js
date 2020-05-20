import React from "react";
import { withRouter } from 'react-router-dom';

export const Currency = withRouter(({...props}) => {
    const { params } = props.match;
    const { lang, curr } = params;
    const{ history } = props;
    return (
        <CurrencyBase   lang={lang}
                        curr={curr}
                        history={history}/>
    );
});

export const CurrencyBase = (props) => {
    const { lang, curr, history } = props;

    const changeCurr = (e) => {
        e.preventDefault();
        history.push('/' +lang + '/' + e.currentTarget.id);
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
