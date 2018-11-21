import React, { Component } from 'react';
import apiConfig from './config/config';
import store from './store';
import Header from './components/Header';
import Signup from './components/Signup';
import * as session from './services/session';
import * as api from './services/api';
import t from 'typy';

/*
  const routeStack = [
  	{ name: 'Main', component: Main },
  	{ name: 'Login', component: Login },
  	{ name: 'Register', component: Register },
  	{ name: 'Users', component: Users },
  ];
*/

class App extends Component {
  constructor(props) {
    super(props);

    this.initialState = {
        isLoading: false,
        error: null,
        authenticated: null,
        email: '',
        password: ''
    };
    this.state = this.initialState;
  }

  async fetchCustomer() {
    console.log('called fetchCustomer');
    if(!this.state.authenticated) {return;}
    await
    fetch(
        apiConfig.url+'/api/Party/UserName/'+this.state.customer.partyUser.username, {
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


  componentDidMount() {
		// Waits for the redux store to be populated with the previously saved state,
		// then it will try to auto-login the user.
		const unsubscribe = store.subscribe(() => {
			if (store.getState().services.persist.isHydrated) {
				unsubscribe();
				this.autoLogin();
			}
		});
	}

	autoLogin() {
		session.refreshToken().then(() => {
		//	this.setState({ initialRoute: routeStack[3] });
		}).catch(() => {
		//	this.setState({ initialRoute: routeStack[0] });
		});
	}

  loginClick = async (event) => {

		session.authenticate(this.state.email, this.state.password)
		.then(() => {
      this.setState({
        authenticated: true
      })
			//this.setState(this.initialState);
      console.log(this.state);
      session.refreshToken();
			//const routeStack = this.props.navigator.getCurrentRoutes();
			//this.props.navigator.jumpTo(routeStack[3]);
		})
		.catch((exception) => {
			// Displays only the first error message
			const error = api.exceptionExtractError(exception);
			this.setState({
				isLoading: false,
				...(error ? { error } : {}),
			});

			if (!error) {
				throw exception;
			}
		});

  }

  deepValue(obj, path, value) {
          var parts = path.split('.');
          var curr = obj;
          for(var i=0;i<parts.length-1;i++)
              curr = curr[parts[i]] || {};
          curr[parts[parts.length-1]] = value;
  }

  updateCustomerState = (event) =>  {
    //console.log('id = ' + event.target.id + ' : value = ' + event.target.value);
    let newstate = {...this.state};
    this.deepValue(newstate, event.target.id, event.target.value);
    this.setState({
       'email': newstate.email,
       'password': newstate.password
    });
    //console.log(this.state);
    // let newcustomer = {...this.state.customer};
    // console.log(event.target.id + ' is defined = ' + t(newcustomer, event.target.id).safeObject);
    // console.log('Updating ' + event.target.id + ' with value = ' + event.target.value);
    // this.deepValue(newcustomer, event.target.id, event.target.value);
    // this.setState({
    //  'customer' : newcustomer
    // });
    // console.log('The state is set to ' + t(newcustomer, event.target.id).safeObject) ;
  }

  render() {
    const {customer, isLoading} = this.state;
    return (
        <div className="App">
          <link rel="stylesheet"
                href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
                integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
                crossOrigin="anonymous"
          />
          <Header authenticated={(this.state.authenticated)}
                  loginClick={this.loginClick.bind(this)}
                  updateCustomerState={this.updateCustomerState.bind(this)}
                  email={this.state.email}
                  password={this.state.password}
          />
        <Signup
                  updateCustomerState={this.updateCustomerState.bind(this)}
        />
        </div>
    );
  }
}

export default App;
