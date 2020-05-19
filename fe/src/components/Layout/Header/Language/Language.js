import React from "react";
import { withRouter } from 'react-router-dom';



export const Language = withRouter(({...props}) => {
    const { params } = props.match;
    const { lang, curr } = params;
    const{ history } = props;

    return (
        <LanguageBase lang={lang}
                      curr={curr}
                      history={history}/>
    );
});

export const LanguageBase = (props) => {
    const { lang, curr, history } = props;

    const changeLang = (e) => {
        e.preventDefault();
        history.push('/' + e.currentTarget.id + '/' + curr);
    }

    return (
        <li data-testid="language"> <a id="language" href="#">{lang}<i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="en-GB" onClick={changeLang}>English</a></li>
                <li><a href="#" id="zh-HK" onClick={changeLang}>Chinese</a></li>
            </ul>
        </li>
    );
};

export default Language;






