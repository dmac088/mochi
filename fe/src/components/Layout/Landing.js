import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { authenticate } from "../../actions/SessionActions";

export class Landing extends Component {

  //use localstate to store the username and password
  constructor(props) {
    super(props);
    this.state = {
                    username: null,
                    password: null,
                 };
  }  

  setUsername = (event) => {
    this.setState({
      username:  event.currentTarget.value,
    });
  }
  
  setPassword = (event) => {
    this.setState({
      password:  event.currentTarget.value,
    });
  }

  render() {
    const { authenticate } = this.props;
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
                    onChange={this.setUsername}
                    id="customer.userName"  
                    placeholder="User Name"
                    aria-label="userName" />
                  <br />
                  <input
                    className="form-control mr-sm-2"
                    id="customer.password"
                    type="password"
                    onChange={this.setPassword}
                    placeholder="Password"
                    aria-label="Password" />
                  <hr />
                  <button
                    className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
                    onClick={() => authenticate(this.state.username,
                                                this.state.password)}
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
                      {authenticate})
                      (Landing);

