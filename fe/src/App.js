import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    customers: [],
	access_token: ''
  };

  async componentDidMount() {
    let details = {
        "client_id": "spring-security-oauth2-read-write-client", 
		"username": "admin",
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
		"https://localhost:8090/oauth/token", {
		crossDomain: true,
		method: "POST", 
		headers: new Headers({
			"Authorization": "Basic "+btoa('spring-security-oauth2-read-write-client:spring-security-oauth2-read-write-client-password1234'),
			"Content-Type": "application/x-www-form-urlencoded",
			"Cache-Control": "no-cache"
		}),
		 body: formBody
	});
    const body = await response.text();
    console.log(JSON.parse(body).access_token);
    this.setState({ access_token: JSON.parse(body).access_token, isLoading: false });
	
	
	const response2 = await 
	fetch(
		"https://localhost:8090/api/Customer", {
		crossDomain: true,
		method: "GET", 
		headers: new Headers({
			"Authorization": 	"Bearer "+this.state.access_token,
			"Content-Type": 	"application/json"
		})
	});  
	
	const body2 = await response2.text();
	this.setState({ customers: JSON.parse(body2), isLoading: false })
  }
  
  

  render() {
    const {customers, isLoading} = this.state;
    console.log(this.state.customers);
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
          {customers.map(customer =>
            <div id={customer.partyId}> 
              {customer.customerId}
            </div>
          )}
        </div>
      </div>
    );
  }
}

export default App;