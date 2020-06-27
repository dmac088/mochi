import React, { useState } from 'react';
import { useDispatch } from 'react-redux'
import { register } from '../../../services/Customer';

function Register() {

  const [stateObject, setObjectState] = useState({
    givenName: null,
    familyName: null, 
    userName: null,
    password: null,
    confirmPassword: null,
  });
  
  const dispatch = useDispatch();

  const setGivenName = (e) => {
    e.preventDefault();
    const value = e.target.value;
    setObjectState((prevState) => ({ 
      ...prevState, 
      givenName: value,
    }));
  }

  const setFamilyName = (e) => {
    e.preventDefault();
    const value = e.target.value;
    setObjectState((prevState) => ({ 
      ...prevState, 
      familyName: value,
    }));
  }

  const setUsername = (e) => {
    e.preventDefault();
    const value = e.target.value;
    setObjectState((prevState) => ({ 
      ...prevState, 
      userName: value,
    }));
  }
  
  const setPassword = (e) => {
    e.preventDefault();
    const value = e.target.value;
    setObjectState((prevState) => ({ 
      ...prevState, 
      password: value,
    }));
  }
  
  const setConfirmPassword = (e) => {
    e.preventDefault();
    const value = e.target.value;
    setObjectState((prevState) => ({ 
      ...prevState, 
      confirmPassword: value,
    }));
  }

  const registerCustomer = (e) => {
    e.preventDefault();
    dispatch(register(stateObject));
  }

  return (
    <form action="#">
      <div className="login-form">
        <h4 className="login-title">Register</h4>
        <div className="row">
          <div className="col-md-6 col-12 mb-20">
            <label>First Name</label>
            <input id="customer.givenName" onChange={setGivenName} className="mb-0" type="text" placeholder="Given Name" />
          </div>
          <div className="col-md-6 col-12 mb-20">
            <label>Last Name</label>
            <input id="customer.familyName" onChange={setFamilyName} className="mb-0" type="text" placeholder="Family Name" />
          </div>
          <div className="col-md-12 mb-20">
            <label>Email Address*</label>
            <input id="customer.userName" onChange={setUsername} className="mb-0" type="email" placeholder="Email Address" />
          </div>
          <div className="col-md-6 mb-20">
            <label>Password</label>
            <input id="customer.password" onChange={setPassword} className="mb-0" type="password" placeholder="Password" />
          </div>
          <div className="col-md-6 mb-20">
            <label>Confirm Password</label>
            <input  onChange={setConfirmPassword}  className="mb-0" type="password" placeholder="Confirm Password" />
          </div>
          <div className="col-12">
            <button className="register-button mt-0" onClick={registerCustomer}>Register</button>
          </div>
        </div>
      </div>
    </form>
  )
}

export default Register;
