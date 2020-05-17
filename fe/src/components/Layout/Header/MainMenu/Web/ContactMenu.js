import React from "react";
import { withRouter } from "react-router-dom";

const routeContact = (e, params, history) => {
    e.preventDefault();
    history.push('/' + params.lang + '/' + params.curr + '/contact');
  }

export const ContactMenu = withRouter(({history, ...props}) => {
    console.log(props);
    const { params } = props.match;
    if(!params) { return null; }
    return (
        <li className="active">
            <a href="#" onClick={(e) => routeContact(e, params, history)} >
                Contact
            </a>
        </li>
    );
});

export default ContactMenu;