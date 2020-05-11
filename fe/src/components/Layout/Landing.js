import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { setUsername, setPassword, authenticate } from "../../actions/SessionActions";

export class Landing extends Component {
  render() {
    const { setUsername, setPassword, authenticate } = this.props;
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
                    onChange={setUsername}
                    id="customer.userName"
                    placeholder="User Name"
                    aria-label="userName" />
                  <br />
                  <input
                    className="form-control mr-sm-2"
                    id="customer.password"
                    type="password"
                    onChange={setPassword}
                    placeholder="Password"
                    aria-label="Password" />
                  <hr />
                  <button
                    className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
                    onClick={authenticate}
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

const mapStateToProps = state => ({
  session: state.session,
})

export default connect(mapStateToProps,
  {setUsername, setPassword, authenticate})
  (Landing);

