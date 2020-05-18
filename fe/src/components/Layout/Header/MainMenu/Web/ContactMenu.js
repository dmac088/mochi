import React from 'react';
import { withRouter } from 'react-router-dom';

export const route = (e, props) => {
    e.preventDefault();
    const { lang, curr } = props.match.params;
    props.history.push('/' + lang + '/' + curr + '/contact');
}

export const ContactMenu = withRouter(({...props}) => {
   console.log(props);
    return (
      <li>
        <a onClick={(e) => route(e, props)} href="#">CONTACT</a>
      </li>
    );
});

export default ContactMenu;