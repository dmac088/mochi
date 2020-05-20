import React from "react";
import { withRouter } from 'react-router-dom';

export const MyWishList = withRouter(({...props}) => {
    const { params } = props.match;
    const { lang, curr } = params;
    const{ history } = props;
        
    return (
        <MyWishListBase lang={lang}
                        curr={curr}
                        history={history} />
    );
});

export const MyWishListBase = (props) => {
    const { lang, curr, history } = props;

    const routeWish = (e) => {
        e.preventDefault();
        props.history.push('/' + lang + '/' + curr + '/MyWishList');
    }

    return (
        <li> 
            <a href="#" onClick={routeWish}>My WishList</a>
        </li>
    );
};

export default MyWishList;
