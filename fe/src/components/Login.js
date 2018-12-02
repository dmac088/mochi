import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as session from '../services/session';
import store from '../store';
import { deepValue } from '../services/api';
import { initialState } from '../services/customer/reducer';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = initialState;
    store.subscribe(this.reduxSubscribedFunction);
  }

  reduxSubscribedFunction = () => {
    //console.log('login subscribed function triggered');
  }

  autoLogin = () =>  {
    session.refreshToken().then(() => {
      console.log('the token has been refreshed');
    //	this.setState({ initialRoute: routeStack[3] });
    }).catch(() => {
      console.log('the token has not been refreshed');
    //	this.setState({ initialRoute: routeStack[0] });
    });
  }

  updateCustomerState = (event) =>  {
    let newstate = {...this.state};
    deepValue(newstate, event.target.id, event.target.value);
    this.setState(newstate);
  }

  loginClick = (event) => {
    session.authenticate(this.state.customer);

  }

  logoutClick = (event) => {
    session.clearSession();
    this.setState(null);
  }

  renderLogoutButton = () => {
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

    renderuserNameField = () => {
     let userNameField;
     if(!this.props.tokens.authenticated) {
       userNameField =
       <input
         className="form-control mr-sm-2"
         type="input"
         id="customer.userName"
         onChange={this.updateCustomerState}

         placeholder="User Name"
         aria-label="userName" />;
     }
     return userNameField;
    }

    renderPasswordField = () => {
     let passwordField;
     if(!this.props.tokens.authenticated) {
       passwordField =
       <input
           className="form-control mr-sm-2"
           id="customer.password"
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
          {this.renderuserNameField()}
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
