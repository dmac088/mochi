import React from "react"; 
import {withRouter} from 'react-router'

export const HomeMenu = withRouter((props) => {
    const { history } = props;
    return(    
      <li className="active">
        <a onClick={(e) => history.push('/')} >HOME</a>
      </li>
    );
});

export default HomeMenu;