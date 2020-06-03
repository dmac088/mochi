import React from 'react';
import { withRouter } from 'react-router-dom';

export const route = (e, props) => {
    e.preventDefault();
    const { lang, curr } = props.match.params;
    props.history.push('/' + lang + '/' + curr);
}

export const HomeMenu = withRouter(({ ...props }) => {
    return (
        <HomeMenuBase {...props} />
    );
});

export const HomeMenuBase = ({ ...props }) => {
    return (
        <a onClick={(e) => route(e, props)} href="#">HOME</a>
    );
};

export default HomeMenu;