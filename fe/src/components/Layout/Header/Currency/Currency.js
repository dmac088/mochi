import React from "react";
import { generatePath } from 'react-router';

const changeCurr = (e, props) => {
    e.preventDefault();
    const { match, history, location} = props;
    const curr = e.currentTarget.id;
    const { lang } = match.params; 
    const { path } = match;
    const newPath = generatePath(path, { 
                                        ...match.params,
                                        lang: lang,
                                        curr: curr 
                                    }) + location.search;
                                         
    history.replace(newPath);
} 

export const Currency = (props) => {
    const { match } = props;
    const { curr } = match.params;

    return (
        <li> <a id="currency" href="#">{curr}<i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="HKD" onClick={(e) => changeCurr(e, props)}>HKD</a></li>
                <li><a href="#" id="USD" onClick={(e) => changeCurr(e, props)}>USD</a></li>
            </ul>
        </li>
    ); 
};

export default Currency;
