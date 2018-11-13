import React, { Component } from 'react';
import logo from './logo.svg';
import apiConfig from './config';
import Header from './components/Header';
import Login from './components/Login';
import Signup from './components/Signup';

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

  loginClick = async (event) => {
    console.log('Login clicked');
    await this.authUser();
    await this.fetchData();
  }

  logoutClick = (event) => {
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

  render() {
    const {customer, isLoading} = this.state;
    return (
        <div className="App">
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossOrigin="anonymous" />
          <Header authenticated={(this.state.authenticated)}
                  loginClick={this.loginClick.bind(this)}
                  logoutClick={this.logoutClick.bind(this)}
                  updateUsernameValue={this.updateUsernameValue.bind(this)}
                  updatePasswordValue={this.updatePasswordValue.bind(this)}
                  customer={this.state.customer}
          />
        <Signup/>
        </div>
    );
  }
}

export default App;
