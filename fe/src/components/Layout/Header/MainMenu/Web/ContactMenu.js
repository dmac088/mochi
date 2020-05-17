import React from "react";
import { Link } from 'react-router-dom';

export const ContactMenu = (props) => {
    const { history } = props;
    return (
        <li className="active">
            <Link to="/contact">
                Contact
            </Link>
        </li>
    );
}

export default ContactMenu;