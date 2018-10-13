import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    customers: []
  };

  async componentDidMount() {
    const response = await fetch('/api/Person');
    const body = await response.json();
	console.log(body);
    this.setState({ customers: body, isLoading: false });
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
          <h2>JUG List</h2>
          {customers.map(customer =>
            <div key={customer.partyId}>
              {customer.givenNameEn}
            </div>
          )}
        </div>
      </div>
    );
  }
}

export default App;