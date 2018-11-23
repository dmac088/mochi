import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as session from '../services/session';
import * as api from '../services/api';
import * as usersApi from '../data/users/api';
import store from '../store';
import { deepValue } from '../services/api';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = this.initialStateLogin;
    store.subscribe(this.reduxSubscribedFunction);
  }

  reduxSubscribedFunction = () => {
    console.log('subscribed function triggered');
  }

  updateCustomerState = (event) =>  {
    console.log('customer state changed');
    let newstate = {...this.state};
    deepValue(newstate, event.target.id, event.target.value);
    this.setState({
       'username': newstate.username,
       'password': newstate.password,
       'firstName': newstate.firstName,
    });
  }

  loginClick = (event) => {
    session.authenticate(this.state.username, this.state.password)
    .then(() => {
      console.log('user authentication complete');
    })
    .catch((exception) => {
      // Displays only the first error message
      const error = api.exceptionExtractError(exception);
      this.setState({
        isLoading: false,
        ...(error ? { error } : {}),
      });

      if (!error) {
        throw exception;
      }
    });
  }

  logoutClick = (event) => {
    session.clearSession();

    this.setState({
      initialStateLogin
    });
  }

  renderLogoutButton = () => {
    // console.log('render logout button');
     let button;
     if(this.props.authenticated) {
       button = <button
            onClick={this.logoutClick}
            className="btn btn-outline-success mr-sm-5 my-2 my-sm-0">
          Logout
       </button>;
     }
     return button;
    }

  renderLoginButton = () => {
    // console.log('render login button');
     let button;
     if(!this.props.authenticated) {
       button =
       <button
           onClick={this.loginClick}
           className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
           type="submit">
          Login
      </button>;
     }
     return button;
    }

  rendersignupButton = () => {
    // console.log('render signup button');
     let button;
     if(!this.props.authenticated) {
       button =
       <button
           onClick={this.signupClick}
           className="btn btn-outline-success mr-sm-5 my-2 my-sm-0"
           type="submit">
          SignUp
      </button>;
     }
     return button;
    }

    renderUserNameField = () => {
  //   console.log('render username field');
     let userNameField;
     if(!this.props.authenticated) {
       userNameField =
       <input
         className="form-control mr-sm-2"
         type="input"
         id="username"
         onChange={this.updateCustomerState}

         placeholder="Username"
         aria-label="Username" />;
     }
     return userNameField;
    }

    renderPasswordField = () => {
    // console.log('render password field');
     let passwordField;
     if(!this.props.authenticated) {
       passwordField =
       <input
           className="form-control mr-sm-2"
           id="password"
           type="password"
           onChange={this.updateCustomerState}

           placeholder="Password"
           aria-label="Password" />;
     }
     return passwordField;
    }



  render() {
      return(
        <div>
          {this.renderUserNameField()}
          {this.renderPasswordField()}
          {this.renderLoginButton()}
          {this.renderLogoutButton()}
          {this.rendersignupButton()}
        </div>
      );
    }
}


const mapStateToProps = (state) => {
  return {
    //take value from reducer, alias used in combinReducers in ./data/reducer.js
    user: state.services.session.user
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    //take value from reducer, alias used in combinReducers in ./data/reducer.js
    setAuthenticated: (auth) => {
      dispatch({
        type: "SET_AUTH",
        payload: auth
      })
    }
  };
};


const initialStateLogin = () => {
  return  JSON.parse('{"isLoading": false, "error": null, "username": "", "password": "", "firstName": ""}');
};


export default connect(mapStateToProps,mapDispatchToProps)(Login);

// {(!props.authenticated) ? 'Username:' : '' } {props.renderUserNameField()}<br/>
// {(!props.authenticated) ? 'Password:' : '' } {props.renderPasswordField()}<br/>
// {props.renderLogoutButton()}
// {props.renderLoginButton()}
// <Greeting authenticated={props.authenticated} givenNameEn={props.customer.givenNameEn}/>
