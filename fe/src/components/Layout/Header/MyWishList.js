import React from "react";
import { withRouter } from 'react-router-dom';

const routeWish = (e, props) => {
    e.preventDefault();
    const { lang, curr } = props.match.params;
    props.history.push('/' + lang + '/' + curr + '/MyWishList');
}

export const MyWishList = withRouter(({...props}) => {
    return (
        <MyWishListBase {...props} />
    );
});

export const MyWishListBase = ({...props}) => {
    return (
        <li> 
            <a href="#" onClick={(e) => routeWish(e, props)}>My WishList</a>
        </li>
    );
};

export default MyWishList;
