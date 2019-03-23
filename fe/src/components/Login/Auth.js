import React, { Component } from 'react';
import Register from './Register';
import Login from './Login';

class Auth extends Component {

  render() {
    return(
      <React.Fragment>
        <div className="page-content mb-50">
          <div className="container">
            <div className="row">
              <div className="col-sm-12 col-md-12 col-xs-12 col-lg-6 mb-30">
                <Login
                  {...this.props}
                />
              </div>
              <div className="col-sm-12 col-md-12 col-xs-12 col-lg-6">
                <Register
                  {...this.props}
                />
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    )
  }
}

export default Auth;
