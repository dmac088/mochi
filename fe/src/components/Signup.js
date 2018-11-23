import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as session from '../services/session';
import * as api from '../services/api';
import * as usersApi from '../data/users/api';
import store from '../store';
import { deepValue } from '../services/api';
//signup should have it's own local state and not be bound to App.js

class Signup extends Component {

  constructor(props) {
    super(props);
    this.state = initialStateSignup();
    store.subscribe(this.reduxSubscribedFunction);
  }

  reduxSubscribedFunction = () => {
    console.log('subscribed function triggered');
  }

  updateCustomerState = (event) =>  {
    let newstate = {...this.state};
    deepValue(newstate, event.target.id, event.target.value);
    this.setState({
       'username': newstate.username,
       'password': newstate.password,
       'firstName': newstate.firstName,
    });
  }

  signupClick = (event) => {
    console.log("signup click");
    this.setState({
      isLoading: true,
      error: '',
    });

    console.log(this.state.firstName);
    console.log(this.state.username);
    console.log(this.state.password);

    const { firstName, username, password } = this.state;
    usersApi.create({ firstName, username, password })
    .then(() => {
      session.authenticate(username, password)
      .then(() => {
        //the following is fine since child component props are listening to the redux state
        //therefore there is no problem with overriding local state
        this.setState(this.initialStateSignup);
        //const routeStack = this.props.navigator.getCurrentRoutes();
        //this.props.navigator.jumpTo(routeStack[3]);
      });
    })
    .catch((exception) => {
      // Displays only the first error message
      const error = api.exceptionExtractError(exception);
      const newState = {
        isLoading: false,
        ...(error ? { error } : {}),
      };
        this.setState(newState);
    });
  }

  render() {
      return(
        <div className="row">
          <div className="col-md-3 order-md-1">
          </div>
        <div className="col-md-6 order-md-2">
          <h4 className="mb-3">
          Sign Up
          </h4>
          <div className="needs-validation" noValidate>
            <div className="row">
              <div className="col-md-6 mb-3">
                <label htmlFor="firstName">
                First name
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="firstName"
                  onChange={this.updateCustomerState}
                  placeholder="First Name"
                  required />
                <div className="invalid-feedback">
                  Valid first name is required.
                </div>
              </div>
              <div className="col-md-6 mb-3">
                <label htmlFor="lastName">
                Last name
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="familyNameEn"
                  onChange={this.updateCustomerState}
                  placeholder="Last Name"
                  required />
                <div className="invalid-feedback">
                  Valid last name is required.
                </div>
              </div>
            </div>
            <div className="mb-3">
              <label htmlFor="username">Username</label>
              <div className="input-group">
                <input
                  type="text"
                  className="form-control"
                  id="username"
                  onChange={this.updateCustomerState}
                  placeholder="you@placeholder.com"
                  required />
                                  <div
                  className="invalid-feedback"
                  style={{width: '100%'}}>
                  Your username is required.
                </div>
              </div>
            </div>
            <div className="mb-3">
              <label htmlFor="passwordField">
                Password <span className="text-muted"/>
              </label>
              <input
                type="password"
                className="form-control"
                id="password"
                onChange={this.updateCustomerState}
                placeholder="Password" />
            </div>

            <hr className="mb-4" />

            <button
              onClick={this.signupClick}
            className="btn btn-primary btn-lg btn-block">
            Submit
            </button>
        </div>
        <div className="col-md-3 order-md-3">
        </div>
      </div>
    </div>
      );
  }
}


const initialStateSignup = () => {
  return  JSON.parse('{"isLoading": false,  "error": null,"username": "","password": "","firstName": ""}');
};


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

export default connect(mapStateToProps,mapDispatchToProps)(Signup);
