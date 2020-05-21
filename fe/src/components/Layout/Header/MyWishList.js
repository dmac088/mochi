import React from "react";
import { withRouter } from 'react-router-dom';
import { routeToPage } from '../../../services/Routing/Helper';

export const MyWishList = withRouter(({...props}) => {
    const { params } = props.match;
    const{ history } = props;

    return (
        <MyWishListBase params={params}
                        history={history} />
    );
});

export const MyWishListBase = (props) => {
    const { params, history } = props;

    const routeWish = (e) => {
        e.preventDefault();
        routeToPage(history, params, 'MyWishList');
    }

    return (
        <li> 
            <a href="#" onClick={routeWish}>My WishList</a>
        </li>
    );
};

export default MyWishList;
