import React from "react";
import { withRouter } from 'react-router-dom';

const changeLang = (e, props) => {
    e.preventDefault();
    const { curr } = props.match.params;
    props.history.push('/' + e.currentTarget.id + '/' + curr);
}

export const Language = withRouter(({...props}) => {
    const { lang } = props.match.params;
    return (
        <li> <a href="#">{lang} <i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a href="#" id="en-GB" onClick={(e) => changeLang(e, props)}>English</a></li>
                <li><a href="#" id="zh-HK" onClick={(e) => changeLang(e, props)}>Chinese</a></li>
            </ul>
        </li>
    );
});

export default Language;






