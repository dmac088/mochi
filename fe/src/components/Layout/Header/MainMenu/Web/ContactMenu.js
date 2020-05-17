import React from "react";
import { withRouter } from 'react-router'
import { Link } from 'react-router-dom';

export const ContactMenu = withRouter((props) => {
    const { history } = props;
    return (
        <li className="active">
            <Link to="/contact">
                Contact
            </Link>
        </li>
    );
});

export default ContactMenu;