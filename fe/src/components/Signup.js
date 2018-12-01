import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as customerService from '../services/customer';
import * as sessionService from '../services/session';
import * as api from '../services/api';
import store from '../store';
import { deepValue } from '../services/api';

class Signup extends Component {

  constructor(props) {
    super(props);
    this.state = {
                    customer: {
                        userName: null,
                        password: null,
                        givenName: null,
                        familyName: null
                    },
                    isLoading: null,
                    error: null
                };
    store.subscribe(this.reduxSubscribedFunction);
  }

  reduxSubscribedFunction = () => {
  }

  updateCustomerState = (event) =>  {
    let newstate = {...this.state};
    deepValue(newstate, event.target.id, event.target.value);
    this.setState(newstate);
  }

  signupClick = (event) => {
      console.log("signup click");
      this.setState({
        isLoading: true,
        error: '',
      });
      customerService.createNewCustomer(this.state.customer);
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
                <label htmlFor="givenName">
                Given Name
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="customer.givenName"
                  onChange={this.updateCustomerState}
                  placeholder="Given Name"
                  required />
                <div className="invalid-feedback">
                  Valid given name is required.
                </div>
              </div>
              <div className="col-md-6 mb-3">
                <label htmlFor="familyName">
                Family name
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="customer.familyName"
                  onChange={this.updateCustomerState}
                  placeholder="Family Name"
                  required />
                <div className="invalid-feedback">
                  Valid family name is required.
                </div>
              </div>
            </div>
            <div className="mb-3">
              <label htmlFor="userName">userName</label>
              <div className="input-group">
                <input
                  type="text"
                  className="form-control"
                  id="customer.userName"
                  onChange={this.updateCustomerState}
                  placeholder="you@placeholder.com"
                  required />
                                  <div
                  className="invalid-feedback"
                  style={{width: '100%'}}>
                  Your userName is required.
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
                id="customer.password"
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

const mapStateToProps = (state) => {
  return {
    tokens: state.services.session.tokens,
    customer: state.services.session.customer
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
