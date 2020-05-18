import React from "react";
import { withRouter } from 'react-router-dom';

const routeCheckout = (e, props) => {
    e.preventDefault();
    const { lang, curr } = props.match.params;
    props.history.push('/' + lang + '/' + curr + '/mycheckout');
}

export const MyCheckout = withRouter(({...props}) => {
    return (
        <MyCheckoutBase {...props} />
    );
});

export const MyCheckoutBase = ({...props}) => {
    return (
        <li> 
            <a href="#" onClick={(e) => routeCheckout(e, props)}>My Checkout</a>
        </li>
    );
};

export default MyCheckout;
