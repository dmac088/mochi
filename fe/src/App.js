import React, { Component } from 'react';
import logo from './logo.svg';
import './scss/style.css';
import apiConfig from './config';

import Header from './components/Header';
import Login from './components/Login';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userName: '',
      password: '',
      isLoading: true,
      customer: '',
  	  access_token: '',
      authenticated: false,
      errorResponse: '',
      cartItems: []
    };
  }

  async componentDidMount()  {
    console.log('Component Mounted');
  }


  async authUser() {
    console.log('called authUser');
    let details = {
        'username': this.state.userName,
        'password': this.state.password,
        'grant_type': 'password'
    };

    let formBody = [];
      for (let property in details) {
          let encodedKey = encodeURIComponent(property);
          let encodedValue = encodeURIComponent(details[property]);
          formBody.push(encodedKey + "=" + encodedValue);
      }
      formBody = formBody.join("&");

    const response = await
    fetch(
      apiConfig.url+"/oauth/token", {
      crossDomain: true,
      method: "POST",
      headers: new Headers({
        "Authorization": "Basic "+apiConfig.clientId,
        "Content-Type": "application/x-www-form-urlencoded",
        "Cache-Control": "no-cache"
      }),
       body: formBody
    })
    .then(async (response) => {
      if(response.status === 400) {
          this.setState({
            errorResponse: 'Invalid username or password',
            authenticated: false
          });
          console.log(this.state.errorResponse);
          return;
      }
      const body = await response.text();
      this.setState({
        access_token: JSON.parse(body).access_token,
        isLoading: false,
        authenticated: true
      });
    })
    .catch((error) => {
      console.log('error');
    })
    .finally(() => {
      console.log('finally');
    });
  }

  async fetchData() {
    console.log('called fetchData');
    if(!this.state.authenticated) {return;}
    await
    fetch(
        apiConfig.url+'/api/Customer/'+this.state.userName, {
        crossDomain: true,
        method: "GET",
        headers:  new Headers({
          "Authorization": 	"Bearer "+this.state.access_token,
          "Content-Type": 	"application/json"
        })
    })
     .then(async (response) => await response.json())
     .then((json) => {this.setState({customer: json})
   })
  }

  loginClick = async () => {
    console.log('Login clicked');
    await this.authUser();
    await this.fetchData();
  }

  logoutClick = () => {
    console.log('Logout clicked');
    this.setState({
      authenticated: false,
      customer: '',
      userName: '',
      password: '',
      access_token: ''
    });
  }

  updateUsernameValue = (event) =>  {
    this.setState({
      userName: event.target.value
    });
  }

  updatePasswordValue = (event) => {
    this.setState({
      password: event.target.value
    });
  }

  renderLogoutButton = () => {
   console.log('render logout button');
   let button;
   if(this.state.authenticated) {
     button = <button onClick={this.logoutClick.bind(this)} className='button'>
        Logout
     </button>;
   }
   return button;
  }

  renderLoginButton = () => {
   console.log('render login button');
   let button;
   if(!this.state.authenticated) {
     button = <button onClick={this.loginClick.bind(this)} className='button'>
      Login
    </button>;
   }
   return button;
  }

  renderUserNameField = () => {
   console.log('render username field');
   let userNameField;
   if(!this.state.authenticated) {
     userNameField =  <input onChange={this.updateUsernameValue.bind(this)} />;
   }
   return userNameField;
  }

  renderPasswordField = () => {
   console.log('render password field');
   let passwordField;
   if(!this.state.authenticated) {
     passwordField = <input type='password' onChange={this.updatePasswordValue.bind(this)} />;
   }
   return passwordField;
  }

  render() {
    const {customer, isLoading} = this.state;
    return (
        <div className="App">
          <Header loginBttn={this.renderLogoutButton} cartItems={this.state.cartItems}/>
          <Login  authenticated={(this.state.authenticated)}
                  renderUserNameField={this.renderUserNameField}
                  renderPasswordField={this.renderPasswordField}
                  renderLogoutButton={this.renderLogoutButton}
                  renderLoginButton={this.renderLoginButton}
                  customer={this.state.customer}
          />
        </div>
    );
  }
}

export default App;
