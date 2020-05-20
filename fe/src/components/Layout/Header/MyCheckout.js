import React from "react";
import { withRouter } from 'react-router-dom';



export const MyCheckout = withRouter(({...props}) => {
    const { params } = props.match;
    const { lang, curr } = params;
    const{ history } = props;

    return (
        <MyCheckoutBase lang={lang}
                        curr={curr}
                        history={history} />
    );
});

export const MyCheckoutBase = (props) => {
    const { lang, curr, history } = props;

    const routeCheckout = (e) => {
        e.preventDefault();
        history.push('/' + lang + '/' + curr + '/mycheckout');
    }

    return (
        <li> 
            <a href="#" onClick={routeCheckout}>My Checkout</a>
        </li>
    );
};

export default MyCheckout;
