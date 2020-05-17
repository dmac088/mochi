import React from 'react';
import { withRouter } from 'react-router-dom';

export const route = (e, props) => {
    e.preventDefault();
    console.log(props);
    const { lang, curr } = props.match.params;
    props.history.push('/' + lang + '/' + curr );
}

export const HomeMenu = withRouter(({ ...props }) => {
    
    return (
        <li>
            <a onClick={(e) => route(e, props)} href="#">HOME</a>
        </li>
    );
});

export default HomeMenu;