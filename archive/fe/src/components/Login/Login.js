import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as session from '../../services/session';
import * as tokensActionCreators from '../../services/session/actions';
import * as customerActionCreators from '../../services/customer/actions';
import * as customerSelector from '../../services/customer/selectors';
import { initialState } from '../../services/customer/reducer';
import { withRouter } from "react-router-dom";
import { routeHome, routeLogin, createRouteProps } from '../../services/helpers/routeHelper';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = initialState;
  }

  loginClick = (e) => {
    e.preventDefault();
    const routeProps = createRouteProps(this.props.history, this.props.match, this.props.location);
    session.authenticate( this.state.customer,
                          (e) => routeHome(e, routeProps),
                          (e) => routeLogin(e, routeProps));
  }

  changeUserName = (e) => {
    const customer = {...this.state.customer};
    customer.userName = e.target.value;
    this.setState({
      "customer": customer,
    });
  }

  changePassword = (e) => {
    const customer = {...this.state.customer};
    customer.password = e.target.value;
    this.setState({
      "customer": customer,
    });
  }

  render() {
    return(
      <form action="#" >
        <div className="login-form">
          <h4 className="login-title">Login</h4>
          <div className="row">
            <div className="col-md-12 col-12 mb-20">
              <label>Email Address*</label>
              <input  onChange={this.changeUserName} className="mb-0" type="email" placeholder="Email Address" />
            </div>
            <div className="col-12 mb-20">
              <label>Password</label>
              <input onChange={this.changePassword} className="mb-0" type="password" placeholder="Password" />
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
              <button onClick={this.loginClick} className="register-button mt-0">Login</button>
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
