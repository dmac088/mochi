import React from 'react';

function Register() {
  return (
    <form action="#">
      <div className="login-form">
        <h4 className="login-title">Register</h4>
        <div className="row">
          <div className="col-md-6 col-12 mb-20">
            <label>First Name</label>
            <input id="customer.givenName" onChange={() => {}} className="mb-0" type="text" placeholder="Given Name" />
          </div>
          <div className="col-md-6 col-12 mb-20">
            <label>Last Name</label>
            <input id="customer.familyName" onChange={() => {}} className="mb-0" type="text" placeholder="Family Name" />
          </div>
          <div className="col-md-12 mb-20">
            <label>Email Address*</label>
            <input id="customer.userName" onChange={() => {}} className="mb-0" type="email" placeholder="Email Address" />
          </div>
          <div className="col-md-6 mb-20">
            <label>Password</label>
            <input id="customer.password" onChange={() => {}} className="mb-0" type="password" placeholder="Password" />
          </div>
          <div className="col-md-6 mb-20">
            <label>Confirm Password</label>
            <input className="mb-0" type="password" placeholder="Confirm Password" />
          </div>
          <div className="col-12">
            <button className="register-button mt-0" onClick={() => {}}>Register</button>
          </div>
        </div>
      </div>
    </form>
  )
}

export default Register;
