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
//import { browserHistory } from 'react-router';

class Selector extends Component {

  constructor(props) {
    super(props);
    this.state = initialState;
    store.subscribe(this.reduxSubscribedFunction);
  }

  reduxSubscribedFunction = () => {
    //console.log('login subscribed function triggered');
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
       button =
       <button
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
       <Link to="/Login">
       <button
           onClick={this.loginClick}
           className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
           type="submit">
          Login
      </button>
      </Link>;
     }
     return button;
  }

  rendersignupButton = () => {
     let button;
     if(!this.props.tokens.authenticated) {
       button =
       <Link to="/Signup">
       <button
           onClick={this.signupClick}
           className="btn btn-outline-success mr-sm-5 my-2 my-sm-0"
           type="submit">
          SignUp
      </button>
    </Link>;
     }
     return button;
    }

 signupClick = () => {
   console.log(this.props);
  // browserHistory.push('/Signup')
 }

  render() {
      return(
        <div>
          {this.renderLoginButton()}
          {this.renderLogoutButton()}
          {this.rendersignupButton()}
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
}))(Selector);
