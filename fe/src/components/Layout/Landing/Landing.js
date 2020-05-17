import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from 'react-redux'
import { authenticate, logout } from "../../../actions/SessionActions";
import Header from "../Header/Header";

function Landing() {

  const [username, setUsername] = useState({
    username: null,
  });

  const [password, setPassword] = useState({
    password: null,
  });

  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(authenticate(username, password));
  }

  const tokens = useSelector(state => state.session.tokens);

  return (
    <React.Fragment>
      <Header />
      <div className="landing">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12 text-center">
                <h1 className="display-3 mb-4">Introduction To HATEOAS</h1>
                <form onSubmit={(e) => handleSubmit(e)}>
                  <input
                    className="form-control mr-sm-2"
                    type="input"
                    onChange={e => setUsername(e.target.value)}
                    id="customer.userName"
                    placeholder="User Name"
                    aria-label="userName" />
                  <br />
                  <input
                    className="form-control mr-sm-2"
                    id="customer.password"
                    type="password"
                    onChange={e => setPassword(e.target.value)}
                    placeholder="Password"
                    aria-label="Password" />
                  <button
                    className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
                    type="submit">
                    Login
                  </button>
                </form>
                <hr />
                <button
                  onClick={logout}
                  className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
                  type="submit">
                  Logout
                </button>
                <br />
                <br />
                <Link className="btn btn-lg btn-primary mr-2" to="/dashboard">
                  Load Dashboard
              </Link>
                <br />
                {tokens.access_token}
              </div>
            </div>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
}

export default Landing;

