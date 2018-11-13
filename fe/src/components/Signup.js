import React from 'react';

const Signup = (props) => {
      return(

       <div className="container">
        <div className="row justify-content-center">
        <div className="col-md-6 order-md-1">
          <h4 className="mb-3">
Sign Up
</h4>
          <form className="needs-validation" noValidate>
            <div className="row">
              <div className="col-md-6 mb-3">
                <label htmlFor="firstName">
First name
</label>
                <input
type="text"
className="form-control"
id="firstName"
placeholder="First Name"
defaultValue="First Name"
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
placeholder="Last Name"
defaultValue="Last Name"
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
      </div>
    </div>
      );
  }
export default Signup;

//
// <div className="container">
//  <h1>
//  Sign Up
//  </h1>
//  <p>
//  Please fill in this form to create an account.
//  </p>
//  <hr />
//  <label htmlFor="email">
//    <b>Email</b>
//  </label>
//  <input
//    type="text"
//    placeholder="Enter Email"
//    name="email"
//    required />
//  <label htmlFor="psw">
// <b>Password</b>
//  </label>
//  <input
//    type="password"
//    placeholder="Enter Password"
//    name="psw"
//    required />
//  <label htmlFor="psw-repeat">
//    <b>
//    Repeat Password
//    </b>
//  </label>
//  <input
//      type="password"
//      placeholder="Repeat Password"
//      name="psw-repeat"
//      required />
//  <label>
//    <input
//      type="checkbox"
//      defaultChecked="checked"
//      name="remember"
//      style={{marginBottom: 15}} /> Remember me
//  </label>
//  <p>By creating an account you agree to our <a href="#" style={{color: 'dodgerblue'}}>Terms &amp; Privacy</a>.</p>
//  <div className="clearfix">
//    <button type="button" className="cancelbtn">Cancel</button>
//    <button type="submit" className="signupbtn">
//    Sign Up
//    </button>
//  </div>
// </div>
