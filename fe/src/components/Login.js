import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as session from '../services/session';
import store from '../store';
import { deepValue } from '../services/api';
import { initialState } from '../services/customer/reducer';
import * as tokensActionCreators from '../services/session/actions';
import * as customerActionCreators from '../services/customer/actions';
import { Link } from 'react-router-dom';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = initialState;
    store.subscribe(this.reduxSubscribedFunction);
  }

  reduxSubscribedFunction = () => {
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
    this.setState(initialState);
  }

  renderLoginButton = () => {
     let button;
     if(!this.props.tokens.authenticated) {
       button =
      <Link to="/Landing">
       <button
           onClick={this.loginClick}
           className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
           type="submit">
          Go
      </button>
    </Link>;
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
        <div className="row">
          <div className="col-md-3 order-md-1">
          </div>
          <div className="col-md-6 order-md-2">
            {this.renderuserNameField()}
            {this.renderPasswordField()}
            {this.renderLoginButton()}
          </div>
          <div className="col-md-3 order-md-3">
          </div>
        </div>
      );
    }
}

export default connect(state => ({
    tokens: state.services.session.tokens,
		//routeHistory: state.services.routeHistory,
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokensActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
	},
}))(Login);
