import React from "react";
import { Link } from 'react-router-dom';

export const HomeMenu = () => {
    return (
        <li className="active">
            <Link to="/">
                HOME
        </Link>
        </li>
    );
};

export default HomeMenu;