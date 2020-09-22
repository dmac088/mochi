import React from 'react';
import Register from './Register';
import Login from './Login';

function Auth(props) {
    return(
        <div className="page-content mb-50">
          <div className="container">
            <div className="row">
              <div className="col-sm-12 col-md-12 col-xs-12 col-lg-6 mb-30">
                <Login 
                  {...props}/>
              </div>
              <div className="col-sm-12 col-md-12 col-xs-12 col-lg-6">
                <Register />
              </div>
            </div>
          </div>
        </div>
    );
  }

export default Auth;