import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as customerService from '../../services/customer';
import store from '../../store';
import { deepValue } from '../../services/helpers/Helper';
import { initialState } from '../../services/customer/reducer';
import { bindActionCreators } from 'redux';
import * as tokensActionCreators from '../../services/session/actions';
import * as customerActionCreators from '../../services/customer/actions';


class Register extends Component {

  constructor(props) {
    super(props);
    this.state = initialState;
  }

  updateCustomerState = (event) =>  {
    let newstate = {...this.state};
    deepValue(newstate, event.target.id, event.target.value);
    this.setState(newstate);
  }

  registerClick = (event) => {
      this.setState({
        isLoading: true,
        error: '',
      }, () => {
        customerService.createNewCustomer(this.state.customer);
      });
  }

  render() {
    console.log(store.getState());
    return(
      <form action="#">
        <div className="login-form">
          <h4 className="login-title">Register</h4>
          <div className="row">
            <div className="col-md-6 col-12 mb-20">
              <label>First Name</label>
              <input id="customer.givenName" onChange={this.updateCustomerState} className="mb-0" type="text" placeholder="Given Name" />
            </div>
            <div className="col-md-6 col-12 mb-20">
              <label>Last Name</label>
              <input id="customer.familyName" onChange={this.updateCustomerState} className="mb-0" type="text" placeholder="Family Name" />
            </div>
            <div className="col-md-12 mb-20">
              <label>Email Address*</label>
              <input id="customer.userName" onChange={this.updateCustomerState} className="mb-0" type="email" placeholder="Email Address" />
            </div>
            <div className="col-md-6 mb-20">
              <label>Password</label>
              <input id="customer.password" onChange={this.updateCustomerState} className="mb-0" type="password" placeholder="Password" />
            </div>
            <div className="col-md-6 mb-20">
              <label>Confirm Password</label>
              <input className="mb-0" type="password" placeholder="Confirm Password" />
            </div>
            <div className="col-12">
              <button className="register-button mt-0" onClick={this.registerClick}>Register</button>
            </div>
          </div>
        </div>
      </form>
    )
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
}))(Register);
