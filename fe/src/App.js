import React, { Component } from 'react';
import apiConfig from './config/config';
import Header from './components/Header';
import Signup from './components/Signup';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
              customer: {
                      "@class": ".Person",
                      "partyId": null,
                      "nameCn": "",
                      "familyNameEn": "",
                      "givenNameEn": "initial",
                      "partyType": {
                        "partyTypeDesc": "Person",
                        "partyTypeId": 1
                      },
                      "partyRoles": [
                        {
                          "@class": ".Customer",
                          "roleId": null,
                          "roleType": {
                            "roleTypeDesc": "Customer",
                            "roleTypeId": 1
                          },
                          "customerId": null,
                          "roleStart": null
                        }
                      ],
                      "partyUser": {
                        "username": "",
                        "password": ""
                      }
                    },
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
    console.log('authorizing user: ' + this.state.customer.partyUser.username + ' password: ' + this.state.customer.partyUser.password);
    let details = {
        'username': this.state.customer.partyUser.username,
        'password': this.state.customer.partyUser.password,
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

  async fetchCustomer() {
    console.log('called fetchCustomer');
    if(!this.state.authenticated) {return;}
    await
    fetch(
        apiConfig.url+'/api/Customer/'+this.state.customer.partyUser.username, {
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

  async registerCustomer() {
    console.log('called registerCustomer');
    let details = {
        'customer': this.state.customer
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
      apiConfig.url+"/api/Person", {
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
            errorResponse: 'Could not register, something went wrong'
          });
          console.log(this.state.errorResponse);
          return;
      }
    })
    .catch((error) => {
      console.log('error');
    })
    .finally(() => {
      console.log('finally');
    });

  }

  loginClick = async (event) => {
    console.log('Login clicked');
    await this.authUser();
    await this.fetchCustomer();
  }

  resetState = (event) => {
    console.log('resetting customer state');
    this.setState({customer: {
                            "@class": ".Person",
                            "partyId": "",
                            "nameCn": "",
                            "familyNameEn": "",
                            "givenNameEn": "",
                            "partyType": {
                              "partyTypeDesc": "Person",

                            },
                            "partyRoles": [
                              {
                                "@class": ".Customer",
                                "roleId": null,
                                "roleType": {
                                  "roleTypeDesc": "Customer",
                                },
                                "customerId": null,
                                "roleStart": null
                              }
                            ],
                            "partyUser": {
                              "username": "",
                              "password": ""
                            }
                          },
                access_token: '',
                authenticated: false,
                errorResponse: '',
                cartItems: []
              });
  }

  updateCustomerState = (event) =>  {
    console.log('updating customer attribute ' + event.target.id + ' with value = ' + event.target.value);
    let newcustomer = {...this.state.customer};
    eval('newcustomer.'+ event.target.id + ' = "' + event.target.value +'";');
    this.setState({
      'customer' : newcustomer
    });
    console.log('state is set to ' + eval('this.state.customer.' + event.target.id)) ;
  }

  render() {
    const {customer, isLoading} = this.state;
    return (
        <div className="App">
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossOrigin="anonymous" />
          <Header authenticated={(this.state.authenticated)}
                  loginClick={this.loginClick.bind(this)}
                  resetState={this.resetState.bind(this)}
                  updateCustomerState={this.updateCustomerState.bind(this)}
                  customer={this.state.customer}
          />
        <Signup
                  updateCustomerState={this.updateCustomerState.bind(this)}
        />
        </div>
    );
  }
}

export default App;
