import React from "react";
import { withRouter } from 'react-router-dom';
import { routeToPage } from '../../../services/Routing/Helper';

export const MyBag = withRouter(({...props}) => {
    const { params } = props.match;
    const{ history } = props;

    return (
        <MyBagBase  params={params}
                    history={history} />
    );
});

export const MyBagBase = (props) => {
    const { params, history } = props;

    const routeBag = (e) => {
        e.preventDefault();
        routeToPage(history, params, 'mybag');
    }

    return (
        <li> 
            <a href="#" onClick={routeBag}>My Bag</a>
        </li>
    );
};

export default MyBag;
