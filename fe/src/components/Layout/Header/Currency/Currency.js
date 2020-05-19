import React from "react";
import { withRouter } from 'react-router-dom';

const changeCurr = (e, props) => {
    e.preventDefault();
    const { lang } = props.match.params;
    props.history.push('/' +lang + '/' + e.currentTarget.id);
}

export const Currency = withRouter(({...props}) => {
    return (
        <CurrencyBase {...props}/>
    );
});

export const CurrencyBase = ({...props}) => {
    const { curr } = props.match.params;
    return (
        <li> <a href="#">{curr} <i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="HKD" onClick={(e) => changeCurr(e, props)}>HKD</a></li>
                <li><a href="#" id="USD" onClick={(e) => changeCurr(e, props)}>USD</a></li>
            </ul>
        </li>
    );
};

export default Currency;
