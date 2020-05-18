import React from "react";
import { withRouter } from 'react-router-dom';

const routeBag = (e, props) => {
    e.preventDefault();
    const { lang, curr } = props.match.params;
    props.history.push('/' + lang + '/' + curr + '/MyBag');
}

export const MyBag = withRouter(({...props}) => {
    return (
        <li> 
            <a href="#" onClick={(e) => routeBag(e, props)}>My Bag</a>
        </li>
    );
});

export default MyBag;
