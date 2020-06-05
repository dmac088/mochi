import React from "react";
import { generatePath } from 'react-router';

const changeCurr = (e, match, history) => {
    e.preventDefault();
    const curr = e.currentTarget.id;
    const { lang } = match.params; 
    const { path } = match;
    const newPath = generatePath(path, { lang: lang,
                                         curr: curr } );
    history.replace(newPath);
} 

export const Currency = (props) => {
    const { match, history } = props;
    const { curr } = match.params;

    return (
        <li> <a id="currency" href="#">{curr}<i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="HKD" onClick={(e) => changeCurr(e, match, history)}>HKD</a></li>
                <li><a href="#" id="USD" onClick={(e) => changeCurr(e, match, history)}>USD</a></li>
            </ul>
        </li>
    ); 
};

export default Currency;
