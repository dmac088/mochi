import React from "react";
import  selector  from './lang/selector';
import { generatePath } from 'react-router';

function Language(props) {
    const { match, history } = props; 
    const { lang } = match.params;

    const changeLang = (e, match, history) => {
        e.preventDefault();
        
        const lang = e.currentTarget.id;
        const { curr } = match.params; 
        const { path } = match;
        const newPath = generatePath(path, { lang: lang,
                                             curr: curr } );
           
        history.replace(newPath);
    }

    return (
        <li data-testid="language"> <a id="language" href="#">{selector[lang]}<i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="en-GB" onClick={(e) => changeLang(e, match, history)}>English</a></li>
                <li><a href="#" id="zh-HK" onClick={(e) => changeLang(e, match, history)}>Chinese</a></li>
            </ul>
        </li>
    )
};

export default Language;






