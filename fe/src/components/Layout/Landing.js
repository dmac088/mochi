import React, { Component } from "react";
import { Link } from "react-router-dom";

export class Landing extends Component {
  render() {
    return (
      <div className="landing">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12 text-center">
                <h1 className="display-3 mb-4">Introduction To HATEOAS</h1>
                <input
                  className="form-control mr-sm-2"
                  type="input"
                  id="customer.userName"
                  placeholder="User Name"
                  aria-label="userName" />
                <br />
                <input
                  className="form-control mr-sm-2"
                  id="customer.password"
                  type="password"
                  placeholder="Password"
                  aria-label="Password" />
                <hr />
                <button
                  className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
                  type="submit">
                  Go
                </button>
                <br/>
                <br/>
                <Link className="btn btn-lg btn-primary mr-2" to="/dashboard">
                  Load Dashboard
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Landing;
