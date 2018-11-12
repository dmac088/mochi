import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import apiConfig from './config';
import Greeting from './components/Greeting'

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
      errorResponse: ''
    };
  }

  async componentDidMount()  {
    console.log('Component Mounted');
  }


  async fetchToken() {
    console.log('called fetchToken');
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
        apiConfig.url+"/api/Customer/1", {
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

  async loginClick() {
    console.log('Login clicked');
    await this.fetchToken();
    await this.fetchData();
  }

  logoutClick() {
    console.log('Logout clicked');
    this.setState({
      authenticated: false,
      customer: ''
    });
  }

  updateUsernameValue(event) {
    this.setState({
      userName: event.target.value
    });
  }

  updatePasswordValue(event) {
    this.setState({
      password: event.target.value
    });
  }

  renderLogoutButton() {
    console.log('render logout button');
   let button;
   if(this.state.authenticated) {
     button = <button onClick={(event) => this.logoutClick(event)} className='btn btn-primary'>
        Logout
     </button>;
   }
   return button;
 }


  render() {
    const {customer, isLoading} = this.state;

    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to React</h1>
          </header>

          Username: <input onChange={(event) => this.updateUsernameValue(event)} /><br/>
          Password: <input type='password' onChange={(event) => this.updatePasswordValue(event)} /><br/>
          <button onClick={(event) => this.loginClick(event)} className='btn btn-primary'>Login</button>
          {this.renderLogoutButton()}
          <Greeting isLoggedIn={this.state.isLoggedIn} givenNameEn={this.state.customer.givenNameEn} auth={this.state.authenticated} />

        </div>
    );
  }
}

export default App;
