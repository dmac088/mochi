import React, {Component} from 'react';


class Login extends Component {
    constructor(props){
        super(props);

    }

    renderLogoutButton = () => {
     console.log('render logout button');
     let button;
     if(this.props.authenticated) {
       button = <button
            onClick={this.props.logoutClick}
            className="btn btn-outline-success mr-sm-5 my-2 my-sm-0">
          Logout
       </button>;
     }
     return button;
    }

    renderLoginButton = () => {
     console.log('render login button');
     let button;
     if(!this.props.authenticated) {
       button =
       <button
           onClick={this.props.loginClick}
           className="btn btn-outline-success mr-sm-5 my-2 my-sm-0"
           type="submit">
          Login
      </button>;
     }
     return button;
    }

    renderUserNameField = () => {
     console.log('render username field');
     let userNameField;
     if(!this.props.authenticated) {
       userNameField =  <input
         className="form-control mr-sm-2"
         type="input"
         onChange={this.props.updateUsernameValue}
         placeholder="Username"
         aria-label="Username" />;
     }
     return userNameField;
    }

    renderPasswordField = () => {
     console.log('render password field');
     let passwordField;
     if(!this.props.authenticated) {
       passwordField =
       <input
           className="form-control mr-sm-2"
           type="password"
           onChange={this.props.updatePasswordValue}
           placeholder="Password"
           aria-label="Password" />;
     }
     return passwordField;
    }

  render() {
      return(
        <div>
          {this.renderUserNameField()}
          {this.renderPasswordField()}
          {this.renderLoginButton()}
          {this.renderLogoutButton()}
        </div>
      );
    }
}

export default Login;

// {(!props.authenticated) ? 'Username:' : '' } {props.renderUserNameField()}<br/>
// {(!props.authenticated) ? 'Password:' : '' } {props.renderPasswordField()}<br/>
// {props.renderLogoutButton()}
// {props.renderLoginButton()}
// <Greeting authenticated={props.authenticated} givenNameEn={props.customer.givenNameEn}/>
