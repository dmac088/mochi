import React from 'react';

const Signup = (props) => {
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
                <label htmlFor="firstName">
                First name
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="givenNameEn"
                  onChange={props.updateCustomerState}
                  placeholder="First Name"
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
                  id="familyNameEn"
                  onChange={props.updateCustomerState}
                  placeholder="Last Name"
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
                  id="partyUser.username"
                  onChange={props.updateCustomerState}
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
                type="partyUser.password"
                className="form-control"
                id="password"
                onChange={props.updateCustomerState}
                placeholder="Password" />
            </div>



            <hr className="mb-4" />

            <button
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
export default Signup;
