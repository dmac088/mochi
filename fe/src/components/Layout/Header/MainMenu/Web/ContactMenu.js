import React from "react"; 
import {withRouter} from 'react-router'

export const ContactMenu = withRouter((props) => {
    const { history } = props;
    return(    
      <li className="active">
        <a onClick={(e) => history.push('/Contact')} >Contact</a>
      </li>
    );
});

export default ContactMenu;