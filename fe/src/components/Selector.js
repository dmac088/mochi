import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as session from '../services/session';
import store from '../store';
import { deepValue } from '../services/api';
import { initialState } from '../services/customer/reducer';
import * as customerActionCreators from '../services/customer/actions';
import { Link } from 'react-router-dom';

const Selector = (props) => {

  const reduxSubscribedFunction = () => {
  }

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
              className="btn btn-outline-success mr-sm-5 my-2 my-sm-0">
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
            className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
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
           className="btn btn-outline-success mr-sm-5 my-2 my-sm-0"
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
