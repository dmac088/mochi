import React from 'react';
import { withRouter, Link } from 'react-router-dom';
import { getHomePath } from '../../../Helpers/Route/Route';   

export const HomeMenu = withRouter(({ ...props }) => {
    return (
        <HomeMenuBase {...props} />
    );
});

export const HomeMenuBase = ({ ...props }) => {
    const { match } = props;
    return (
        <Link to={getHomePath(match)}>
            HOME
        </Link> 
    );
};

export default HomeMenu;