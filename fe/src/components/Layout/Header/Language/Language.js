import React from "react";
import  selector  from './lang/selector';
import { generatePath } from 'react-router';

function Language(props) {
    const { match } = props; 
    const { lang } = match.params;

    const changeLang = (e, props) => {
        e.preventDefault();
        const { match, history, location } = props;
        const lang = e.currentTarget.id;
        const { path } = match;
        const newPath = generatePath(path,  {    ...match.params,
                                                lang: lang,
                                            }) + location.search;
           
        history.replace(newPath);
    }

    return (
        <li data-testid="language"> <a id="language" href="#">{selector[lang]}<i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="en-GB" onClick={(e) => changeLang(e, props)}>English</a></li>
                <li><a href="#" id="zh-HK" onClick={(e) => changeLang(e, props)}>Chinese</a></li>
            </ul>
        </li>
    )
};

export default Language;






