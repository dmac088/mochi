import React from "react";
import { withRouter } from 'react-router-dom';
import { routeToPage } from '../../../services/Routing/Helper';

export const MyCheckout = withRouter(({...props}) => {
    const { params } = props.match;
    const{ history } = props;

    return (
        <MyCheckoutBase  params={params}
                         history={history} />
    );
});

export const MyCheckoutBase = (props) => {
    const { params, history } = props;

    const routeCheckout = (e) => {
        e.preventDefault();
        routeToPage(history, params, 'mycheckout');
    }

    return (
        <li> 
            <a href="#" onClick={routeCheckout}>My Checkout</a>
        </li>
    );
};

export default MyCheckout;
