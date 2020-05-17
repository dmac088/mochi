import React from "react";
import { Link } from 'react-router-dom';

export const MyAccount = () => {
    return (
        <li>
            <Link to="/myaccount">
                My Account
            </Link>
        </li>
    );
}

export default MyAccount;


