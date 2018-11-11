import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import apiConfig from './config';
import Greeting from './components/Greeting'

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userName: 'admin',
      password: 'admin1234',
      isLoading: true,
      isLoggedIn: false,
      customer: '',
  	  access_token: ''
    };
  }

  async componentDidMount()  {
    console.log('Component Mounted')
  }

  async fetchData() {
    let details = {
        "username": this.state.userName,
        "password": this.state.password,
        "grant_type": "password"
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
    });

    const body = await response.text();
    const token = JSON.parse(body).access_token;

    if(token !== undefined) {
      console.log('Token is not undefined!');
      if(token.length !== 36) {
        console.log('Token is not 36 characters!');
        return;
      }
    }
    this.setState({ isLoading: false,
                    access_token: token,
                    isLoggedIn: true
                  });

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
     .then((response) => response.json())
     .then((json) => {this.setState({customer: json})
   })
  }

  loginClick() {
    console.log('Login clicked');
    this.fetchData();
  }

  logoutClick() {
    console.log('Logout clicked');
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
          <button onClick={(event) => this.logoutClick(event)} className='btn btn-primary'>Logout</button>
          <Greeting isLoggedIn={this.state.isLoggedIn} givenNameEn={this.state.customer.givenNameEn}/>

        </div>
    );
  }
}

export default App;
