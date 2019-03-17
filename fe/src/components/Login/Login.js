import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as tokensActionCreators from '../../services/session/actions';
import * as customerActionCreators from '../../services/customer/actions';
import { initialState } from '../../services/customer/reducer';
import { withRouter } from "react-router-dom";

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = initialState;
  }

  routeLanding = (props = this.props) => {
    props.history.push('/');
  }

  routeLogin = () => {
    this.props.history.push({
      "pathname": '/Login'
    });
  }

  loginClick = () => {
    session.authenticate(customerSelector.get(),
                          this.routeLanding,
                          this.routeLogin);
  }

  render() {
    return(
      <form action="#" >
        <div className="login-form">
          <h4 className="login-title">Login</h4>
          <div className="row">
            <div className="col-md-12 col-12 mb-20">
              <label>Email Address*</label>
              <input className="mb-0" type="email" placeholder="Email Address" />
            </div>
            <div className="col-12 mb-20">
              <label>Password</label>
              <input className="mb-0" type="password" placeholder="Password" />
            </div>
            <div className="col-md-8">
              <div className="check-box d-inline-block ml-0 ml-md-2 mt-10">
                <input type="checkbox" id="remember_me" />
                <label htmlFor="remember_me">Remember me</label>
              </div>
            </div>
            <div className="col-md-4 mt-10 mb-20 text-left text-md-right">
              <a href="#"> Forgotten pasward?</a>
            </div>
            <div className="col-md-12">
              <button className="register-button mt-0">Login</button>
            </div>
          </div>
        </div>
      </form>
    )
  }
}

export default withRouter(connect(state => ({
    tokens: state.services.session.tokens,
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokensActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
	},
}))(Login));
