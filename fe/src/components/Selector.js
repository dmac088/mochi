import React from 'react';
import * as session from '../services/session';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

const Selector = (props) => {

  const logoutClick = (event) => {
    session.clearSession();
  }

  const renderLogoutButton = () => {
     let button;
     if(props.authenticated) {
       button =
       <Link to="/">
         <Button type="button"
              onClick={logoutClick}
              className="btn btn-outline-success ml-sm-3 mr-sm-3 my-2 my-sm-0">
            Logout
         </Button>
       </Link>;
     }
     return button;
  }

  const renderLoginButton = (props) => {
     let button;
     if(!props.authenticated) {
        button =
        <Link to="/Login">
          <Button type="button">
             Login
         </Button>
       </Link>;
      }
      return button;
  }

  const rendersignupButton = () => {
     let button;
     if(!props.authenticated) {
       button =
       <Link to="/Signup">
       <Button type="submit">
          SignUp
        </Button>
      </Link>;
     }
     return button;
    }

    return(
        <div>
          {renderLoginButton(props)}
          {renderLogoutButton(props)}
          {rendersignupButton(props)}
        </div>
    );
}


export default Selector;
