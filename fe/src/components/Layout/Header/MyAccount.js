import React from "react";
import { withRouter } from 'react-router-dom';
import { routeToPage } from '../../../services/Routing/Helper';

export const MyAccount = withRouter(({...props}) => {
    const { params } = props.match;
    const{ history } = props;

    return (
        <MyAccountBase  params={params}
                        history={history} />
    );
});

export const MyAccountBase = (props) => {
    const { params, history } = props;

    const routeAccount = (e) => {
        e.preventDefault();
        routeToPage(history, params, 'myaccount');
    }

    return (
        <li> 
            <a href="#" onClick={routeAccount}>My Account</a>
        </li>
    );
};

export default MyAccount;
