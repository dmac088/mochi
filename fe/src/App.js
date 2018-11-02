import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    customers: []
  };

  async componentDidMount() {
    const response = await 
	fetch(
		'https://localhost:8090/oauth/token', {
	    crossDomain: true,			
		method: 'POST', 
		headers: new Headers({
			'Authorization':   'Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLXdyaXRlLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtd3JpdGUtY2xpZW50LXBhc3N3b3JkMTIzNA==',
			'Content-Type':    'multipart/form-data',
			'Cache-Control':   'no-cache'
		}),
		 body: JSON.stringify({
			'client_id': 	'spring-security-oauth2-read-write-client', 
			'username': 	'admin',
			'password': 	'admin1234',
			'grant_type':	'password'
		})
	});
    const body = await response.text();
    console.log(JSON.parse(body));

    //this.setState({ customers: body, isLoading: false });
  }

  render() {
    const {customers, isLoading} = this.state;

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
              {customer.givenNameEn}
            </div>
          )}
        </div>
      </div>
    );
  }
}

export default App;