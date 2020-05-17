import React from "react";

const changeLang = (e, props) => {
    e.preventDefault();
    console.log(props);
    props.history.push('/' + e.currentTarget.id + '/HKD');
}

export const Language = (props) => {
    return (
        <li> <a href="#">English <i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a id="en-GB" onClick={(e) => changeLang(e, props)}>English</a></li>
                <li><a id="zh-HK" onClick={(e) => changeLang(e, props)}>Chinese</a></li>
            </ul>
        </li>
    );
}

export default Language;






