import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import apiConfig from './config';

class App extends Component {
  state = {
    isLoading: true,
    customer: '',
	  access_token: ''
  };

  async componentDidMount() {
    let details = {
    		"username": "account-creator",
    		"password": "admin1234",
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
  this.setState({ isLoading: false});
  this.fetchData(JSON.parse(body).access_token);

  }


  async fetchData(token) {
    await
    fetch(
        apiConfig.url+"/api/Customer/1", {
        crossDomain: true,
        method: "GET",
        headers: new Headers({
          "Authorization": 	"Bearer "+token,
          "Content-Type": 	"application/json"
        })
    })
     .then((response) => response.json())
     .then((json) => {this.setState({customer: json})
   })
  }


  render() {
    const {customer, isLoading} = this.state;
    console.log(this.state.customer);
    if (isLoading) {
      return <p>Loading...</p>;
    }

  return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <div className="App-intro">
          <h2>Customer List</h2>

            <div id={customer.partyId} key={customer.partyId}>
              {customer.givenNameEn} {customer.familyNameEn}
            </div>

        </div>
      </div>
    );
  }
}

export default App;
