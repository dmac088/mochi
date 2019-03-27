import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as customerService from '../services/customer';
import store from '../store';
import { deepValue } from '../services/api';
import { initialState } from '../services/customer/reducer';
import { bindActionCreators } from 'redux';
import * as tokensActionCreators from '../services/session/actions';
import * as customerActionCreators from '../services/customer/actions';

class Signup extends Component {

  constructor(props) {
    super(props);
    this.state = initialState;
    store.subscribe(this.reduxSubscribedFunction);
  }

  reduxSubscribedFunction = () => {
  }

  updateCustomerState = (e) =>  {
    let newstate = {...this.state};
    deepValue(newstate, e.currentTartget.id, e.currentTartget.value);
    this.setState(newstate);
  }

  signupClick = (e) => {
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


export default connect(state => ({
    tokens: state.services.session.tokens,
    customer: state.services.session.customer,
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokensActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
	},
}))(Signup);
