import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import { authenticate } from '../../../services/Session/index';
import { getBag } from '../../../services/Bag/index';
import { getForgotPath } from "../Helpers/Route/Route";
import { Form } from 'react-bootstrap';

function Login(props) {

  const { match } = props;

  const [stateObject, setObjectState] = useState({
    username: null,
    password: null,
  });

  const setUsername = (e) => {
    e.preventDefault();
    const value = e.target.value;
    setObjectState((prevState) => ({ 
      ...prevState, 
      username: value,
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

  const dispatch = useDispatch();

  const error = useSelector(state => state.session.error);
  const discovery = useSelector(state => state.discovery);

  const handleSubmit = (e) => {
    e.preventDefault();  
    dispatch(authenticate(discovery, stateObject.username, stateObject.password))
    .then(() => dispatch(getBag()));
  }

  const status = (error) ? error.status : null;
  const data  = (error) ? error.data : null;

  return (
     <Form onSubmit={handleSubmit}>
      <div className="login-form">
        <h4 className="login-title">Login</h4>
        <div className="row">
          <div className="col-md-12 col-12 mb-20">
            <label>Email Address*</label>
            <input onChange={setUsername} className="mb-0" type="email" placeholder="Email Address" required/>
          </div>
          <div className="col-12 mb-20">
            <label>Password</label>
            <input onChange={setPassword} className="mb-0" type="password" placeholder="Password" required/>
          </div>
          <div className="col-md-8">
            <div className="check-box d-inline-block ml-0 ml-md-2 mt-10">
              <input type="checkbox" id="remember_me" />
              <label htmlFor="remember_me">Remember me</label>
            </div>
          </div>
          <div className="col-md-4 mt-10 mb-20 text-left text-md-right">
            <a href={getForgotPath(match)}> Forgot password?</a>
          </div>
          <div className="col-md-12">
            <button type="submit" className="register-button mt-0">Login</button>
          </div>
          <div className="col-md-12">
            {status} - {((data) ? data.error_description : "")}
          </div>
        </div>
      </div>
    </Form>
  )
}

export default Login;

