import React from "react";
import { withRouter } from 'react-router-dom';

const routeAccount = (e, props) => {
    e.preventDefault();
    const { lang, curr } = props.match.params;
    props.history.push('/' + lang + '/' + curr + '/myaccount');
}

export const MyAccount = withRouter(({...props}) => {
    return (
        <li> 
            <a href="#" onClick={(e) => routeAccount(e, props)}>My Account</a>
        </li>
    );
});

export default MyAccount;



