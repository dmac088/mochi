import React from 'react';
import * as session from '../services/session';
import { Link } from 'react-router-dom';

const Selector = (props) => {

  const logoutClick = (event) => {
    session.clearSession();
  }

  const renderLogoutButton = () => {
     let button;
     if(props.authenticated) {
       button =
       <Link to="/">
         <button
              onClick={logoutClick}
              className="btn btn-outline-success ml-sm-3 mr-sm-3 my-2 my-sm-0">
            Logout
         </button>
       </Link>;
     }
     return button;
  }

  const renderLoginButton = (props) => {
     let button;
     if(!props.authenticated) {
        button =
        <Link to="/Login">
        <button
            className="btn btn-outline-success ml-sm-3 mr-sm-2 my-2 my-sm-0"
            type="submit">
           Login
       </button>
       </Link>;
      }
      return button;
  }

  const rendersignupButton = () => {
     let button;
     if(!props.authenticated) {
       button =
       <Link to="/Signup">
       <button
           className="btn btn-outline-success ml-sm-3 mr-sm-3 my-2 my-sm-0"
           type="submit">
          SignUp
        </button>
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
