import React from "react";
import { withRouter } from 'react-router-dom';

const changeLang = (e, props) => {
    e.preventDefault();
    props.history.push('/' + e.currentTarget.id + '/HKD');
}

export const Language = withRouter(({...props}) => {
    return (
        <li> <a href="#">English <i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a id="en-GB" onClick={(e) => changeLang(e, props)}>English</a></li>
                <li><a id="zh-HK" onClick={(e) => changeLang(e, props)}>Chinese</a></li>
            </ul>
        </li>
    );
});

export default Language;






