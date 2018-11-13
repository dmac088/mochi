import React from 'react';

const Signup = (props) => {
      return(
        <div className="row">
          <div className="col-md-2 order-md-1">
          </div>
        <div className="col-md-8 order-md-2">
          <h4 className="mb-3">
Sign Up
</h4>
          <form className="needs-validation" novalidate>
            <div className="row">
              <div className="col-md-6 mb-3">
                <label htmlFor="firstName">
First name
</label>
                <input
type="text"
className="form-control"
id="firstName"
placeholder
defaultValue
required />
                <div className="invalid-feedback">
                  Valid first name is required.
                </div>
              </div>
              <div className="col-md-6 mb-3">
                <label htmlFor="lastName">
Last name
</label>
                <input
type="text"
className="form-control"
id="lastName"
placeholder
defaultValue
required />
                <div className="invalid-feedback">
                  Valid last name is required.
                </div>
              </div>
            </div>
            <div className="mb-3">
              <label htmlFor="username">Username</label>
              <div className="input-group">
                <input
type="text"
className="form-control"
id="username"
placeholder="you@placeholder.com"
required />
                <div
className="invalid-feedback"
style={{width: '100%'}}>
                  Your username is required.
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
id="password"
placeholder="Password" />
            </div>



            <hr className="mb-4" />

            <button
className="btn btn-primary btn-lg btn-block"
type="submit">
Submit
</button>
          </form>
        </div>
        <div className="col-md-2 order-md-3">
        </div>
      </div>
      );
  }
export default Signup;
