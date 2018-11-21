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



  componentDidMount() {
    console.log('componentDidMount');
		// Waits for the redux store to be populated with the previously saved state,
		// then it will try to auto-login the user.
		const unsubscribe = store.subscribe(
                                      //this is a function that the store is subscribing to
                                      //any state changes will trigger this function
                                      //this function is only subscribed on mount
                                      () => {
                                          console.log('subscribed function triggered');
                                    			if (store.getState().services.persist.isHydrated) {
                                            console.log('store is hydrated');
                                    				unsubscribe();
                                            console.log('autoLogin');
                                    				this.autoLogin();
                                    			}
                                          console.log('store is not hydrated');
                                    	});
	}



  //Will I ever need this? Or should I
	autoLogin() {
		session.refreshToken().then(() => {
      console.log('the token has been refreshed');
		//	this.setState({ initialRoute: routeStack[3] });
		}).catch(() => {
      console.log('the token has not been refreshed');
		//	this.setState({ initialRoute: routeStack[0] });
		});
	}

  wait(ms){
     var start = new Date().getTime();
     var end = start;
     while(end < start + ms) {
       end = new Date().getTime();
    }
  }

  loginClick = (event) => {
		session.authenticate(this.state.email, this.state.password)
		.then(() => {
      this.setState({
        authenticated: true
      });
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
