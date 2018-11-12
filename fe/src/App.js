import React, { Component } from 'react';
import logo from './logo.svg';
import './scss/style.css';
import apiConfig from './config';
import Greeting from './components/Greeting';
import Header from './components/Header';

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
     button = <button onClick={(event) => this.logoutClick(event)} className='button'>
        Logout
     </button>;
   }
   return button;
  }

  renderLoginButton() {
    console.log('render login button');
   let button;
   if(!this.state.authenticated) {
     button = <button onClick={(event) => this.loginClick(event)} className='button'>
      Login
    </button>;
   }
   return button;
  }


  render() {
    const {customer, isLoading} = this.state;

    return (
        <div className="App">
          <Header cartItems={this.state.cartItems}/>
      
          Username: <input onChange={(event) => this.updateUsernameValue(event)} /><br/>
          Password: <input type='password' onChange={(event) => this.updatePasswordValue(event)} /><br/>

          {this.renderLogoutButton()}
          {this.renderLoginButton()}
          <Greeting isLoggedIn={this.state.isLoggedIn} givenNameEn={this.state.customer.givenNameEn} auth={this.state.authenticated} />

        </div>
    );
  }
}

export default App;
