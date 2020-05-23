import React from "react";
import { withRouter } from 'react-router-dom';
import { generatePath } from 'react-router';

export const Currency = withRouter(({...props}) => {
    const { history, match } = props;

    return (
        <CurrencyBase   match={match}
                        history={history} />
    );
});

export const CurrencyBase = (props) => {
    const { match } = props;
    const { lang, curr } = match.params;

    const changeCurr = (e) => {
        e.preventDefault();
        const curr = e.currentTarget.id;
        const path = generatePath(match.path, {lang, curr} );
        props.history.replace(path);
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
