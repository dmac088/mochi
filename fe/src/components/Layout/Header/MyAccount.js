import React from "react";
import { withRouter } from 'react-router-dom';

export const MyAccount = withRouter(({...props}) => {
    const { params } = props.match;
    const { lang, curr } = params;
    const{ history } = props;

    return (
        <MyAccountBase  lang={lang}
                        curr={curr}
                        history={history} />
    );
});

export const MyAccountBase = (props) => {
    const { lang, curr, history } = props;

    const routeAccount = (e) => {
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
