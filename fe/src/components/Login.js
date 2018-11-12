import React from 'react';
import '../scss/style.css';
import Greeting from './Greeting';

const Login = (props) => {
      return(
        <div>
          {(!props.authenticated) ? 'Username:' : '' } {props.renderUserNameField()}<br/>
          {(!props.authenticated) ? 'Password:' : '' } {props.renderPasswordField()}<br/>
          {props.renderLogoutButton()}
          {props.renderLoginButton()}
          <Greeting authenticated={props.authenticated} givenNameEn={props.customer.givenNameEn}/>
        </div>
      );
  }
export default Login;
