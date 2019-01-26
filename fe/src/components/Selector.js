import React from 'react';
import * as session from '../services/session';
import { Link } from 'react-router-dom';

const Selector = (props) => {

  const logoutClick = (event) => {
    session.clearSession();
  }

  const renderLogoutbutton = () => {
     let button;
     if(props.authenticated) {
       button =
       <Link to="/">
         <button type="button"
              onClick={logoutClick}
              className="btn btn-outline-success ml-sm-3 mr-sm-3 my-2 my-sm-0">
            Logout
         </button>
       </Link>;
     }
     return button;
  }

  const renderLoginbutton = (props) => {
     let button;
     if(!props.authenticated) {
        button =
        <Link to="/Login">
          <button type="button">
             Login
          </button>
       </Link>;
      }
      return button;
  }

  const rendersignupbutton = () => {
     let button;
     if(!props.authenticated) {
       button =
       <Link to="/Signup">
         <button type="submit">
           SignUp
         </button>
      </Link>;
     }
     return button;
    }

    return(
        <React.Fragment>
            {renderLoginbutton(props)}
            {renderLogoutbutton(props)}
            {rendersignupbutton(props)}
        </React.Fragment>
    );
}


export default Selector;
