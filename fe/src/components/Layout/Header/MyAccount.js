import React from "react";
import { withRouter } from 'react-router-dom';



export const MyAccount = withRouter(({...props}) => {
    return (
        <MyAccountBase {...props} />
    );
});

export const MyAccountBase = (props) => {
    const { lang, curr, history } = props;

    const routeAccount = (e, props) => {
        e.preventDefault();
        history.push('/' + lang + '/' + curr + '/myaccount');
    }

    return (
        <li> 
            <a href="#" onClick={routeAccount}>My Account</a>
        </li>
    );
};

export default MyAccount;
