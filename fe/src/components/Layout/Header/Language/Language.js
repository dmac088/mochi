import React from "react";
import { withRouter } from 'react-router-dom';
import { routeToPage } from '../../../../services/Routing/Helper';

export const Language = withRouter(({...props}) => {
    const { params } = props.match;
    const{ history } = props;

    return (
        <LanguageBase   params={params}
                        history={history} />
    );
});

export const LanguageBase = (props) => {
    const { params, history } = props;   
    const { lang, curr } = params;

    const changeLang = (e) => {
        e.preventDefault();
        routeToPage(history, {curr: curr, lang: e.currentTarget.id}, '');
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






