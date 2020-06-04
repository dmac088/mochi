import React from "react";
import { withRouter } from 'react-router-dom';
import { routeToPage } from '../../../services/Routing/Helper';
import { localization } from './Localization/Localization';

const routePage = 'myaccount';

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
    const {lang, curr} =  params;

    const routeAccount = (e) => {
        e.preventDefault();
        routeToPage(history, params, routePage);
    }

    return (
        <li> 
            <a href="#" onClick={routeAccount}>{localization[lang][routePage]}</a>
        </li>
    );
};

export default MyAccount;
