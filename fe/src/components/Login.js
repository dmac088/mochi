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
    this.state = {};
    store.subscribe(this.reduxSubscribedFunction);
  }

  reduxSubscribedFunction = () => {
    console.log('login subscribed function triggered');
  }

  autoLogin = () =>  {
    console.log('autoLogin');
    session.refreshToken().then(() => {
      console.log('the token has been refreshed');
    //	this.setState({ initialRoute: routeStack[3] });
    }).catch(() => {
      console.log('the token has not been refreshed');
    //	this.setState({ initialRoute: routeStack[0] });
    });
  }

  updateCustomerState = (event) =>  {
    console.log('customer state changed');
    let newstate = {...this.state};
    deepValue(newstate, event.target.id, event.target.value);
    this.setState(newstate);
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
    this.setState(null);
  }

  renderLogoutButton = () => {
    // console.log('render logout button');
     let button;
     if(this.props.tokens.authenticated) {
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
     if(!this.props.tokens.authenticated) {
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
     if(!this.props.tokens.authenticated) {
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
     if(!this.props.tokens.authenticated) {
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
     if(!this.props.tokens.authenticated) {
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
    tokens: state.services.session.tokens
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    //take value from reducer, alias used in combineReducers in ./data/reducer.js
    setAuthenticated: (tokens) => {
      dispatch({
        type: "UPDATE",
        payload: tokens
      })
    }
  };
};

export default connect(mapStateToProps,mapDispatchToProps)(Login);
