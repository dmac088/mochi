import React from "react";
import { withRouter } from 'react-router-dom';

export const MyBag = withRouter(({...props}) => {
    const { params } = props.match;
    const { lang, curr } = params;
    const{ history } = props;
    return (
        <MyBagBase  lang={lang}
                    curr={curr}
                    history={history} />
    );
});

export const MyBagBase = (props) => {
    const { lang, curr, history } = props;

    const routeBag = (e, props) => {
        e.preventDefault();
        history.push('/' + lang + '/' + curr + '/MyBag');
    }

    return (
        <li> 
            <a href="#" onClick={(e) => routeBag(e, props)}>My Bag</a>
        </li>
    );
};

export default MyBag;
