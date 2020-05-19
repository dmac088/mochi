import React from "react";
import { withRouter } from 'react-router-dom';

const changeLang = (e, curr, history) => {
    e.preventDefault();
    history.push('/' + e.currentTarget.id + '/' + curr);
}

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
    return (
        <li data-testid="language"> <a href="#">{lang} <i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="en-GB" onClick={(e) => changeLang(e, curr, history)}>English</a></li>
                <li><a href="#" id="zh-HK" onClick={(e) => changeLang(e, curr, history)}>Chinese</a></li>
            </ul>
        </li>
    );
};

export default Language;






