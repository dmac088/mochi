import React from "react";
import { withRouter } from 'react-router-dom';
import  selector  from './lang/selector';
import { generatePath } from 'react-router';

export const Language = withRouter(({...props}) => {
    const { history, match } = props;

    console.log(history);
    return (
        <LanguageBase   match={match}
                        history={history} />
    );
});

export const LanguageBase = (props) => {
    const { match } = props;
    const { curr, lang } = match.params;

    const changeLang = (e) => {
        e.preventDefault();
        const lang = e.currentTarget.id;
        const path = generatePath(match.path, {lang, curr} );
        props.history.replace(path);
    }

    return (
        <li data-testid="language"> <a id="language" href="#">{selector[lang]}<i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="en-GB" onClick={changeLang}>English</a></li>
                <li><a href="#" id="zh-HK" onClick={changeLang}>Chinese</a></li>
            </ul>
        </li>
    );
};

export default Language;






