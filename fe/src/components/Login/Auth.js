import React, { Component } from 'react';
import Register from './Register';
import Login from './Login';
import BreadCrumb from '../BreadCrumb';

class Auth extends Component {

  render() {
    return(
      <React.Fragment>
      <BreadCrumb
        match={this.props.match}
        page={"Login"}/>
      <div className="page-content mb-50">
        <div className="container">
          <div className="row">
            <div className="col-sm-12 col-md-12 col-xs-12 col-lg-6 mb-30">
              <Login />
            </div>
            <div className="col-sm-12 col-md-12 col-xs-12 col-lg-6">
              <Register />
            </div>
          </div>
        </div>
      </div>
      </React.Fragment>
    )
  }
}

export default Auth;
