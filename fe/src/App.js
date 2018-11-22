import React, { Component } from 'react';
import { connect } from 'react-redux';
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
    this.state = initialState;
  }

  componentDidMount() {
    console.log('componentDidMount');
		// Waits for the redux store to be populated with the previously saved state,
		// then it will try to auto-login the user.
		store.subscribe(this.reduxSubscribedFunction);

	}


  reduxSubscribedFunction = () => {
    console.log('subscribed function triggered');
    if (store.getState().services.persist.isHydrated) {
      console.log('store is hydrated');
      console.log('username = ' + store.getState().services.session.user.username);
      console.log('authenticated = ' + store.getState().services.session.user.authenticated);
    }
  }

	autoLogin() {
    console.log('autoLogin');
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
		session.authenticate(this.state.username, this.state.password)
		.then(() => {
      console.log('user successfully authenticated');
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

  logoutClick = (event) => {
		session.clearSession();

    this.setState({
      initialState
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
    let newstate = {...this.state};
    this.deepValue(newstate, event.target.id, event.target.value);
    this.setState({
       'username': newstate.username,
       'password': newstate.password
    });
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
        <Header authenticated={(this.props.user.authenticated)}
                  loginClick={this.loginClick.bind(this)}
                  logoutClick={this.logoutClick.bind(this)}
                  updateCustomerState={this.updateCustomerState.bind(this)}
                  username={this.props.user.username}
                  password={this.state.password}
          />
        <Signup
                  updateCustomerState={this.updateCustomerState.bind(this)}
        />
        </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    //take value from reducer, alias used in combinReducers in ./data/reducer.js
    user: state.services.session.user
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    //take value from reducer, alias used in combinReducers in ./data/reducer.js
    setAuthenticated: (auth) => {
      dispatch({
        type: "SET_AUTH",
        payload: auth
      })
    }
  };
};


const initialState = () => {
  return        {
                      isLoading: false,
                      error: null,
                      username: '',
                      password: ''
                };
};


export default connect(mapStateToProps,mapDispatchToProps)(App);

//
// async fetchCustomer() {
//   console.log('called fetchCustomer');
//   if(!this.state.authenticated) {return;}
//   await
//   fetch(
//       apiConfig.url+'/api/Party/UserName/'+this.state.username, {
//       crossDomain: true,
//       method: "GET",
//       headers:  new Headers({
//         "Authorization": 	"Bearer "+this.state.access_token,
//         "Content-Type": 	"application/json"
//       })
//   })
//    .then(async (response) => await response.json())
//   // .then((json) => {this.setState({customer: json})
// // })
// }
//
// async registerCustomer() {
//   console.log('called registerCustomer');
//   let details = {
//       'customer': this.state.customer
//   };
//
//   let formBody = [];
//     for (let property in details) {
//         let encodedKey = encodeURIComponent(property);
//         let encodedValue = encodeURIComponent(details[property]);
//         formBody.push(encodedKey + "=" + encodedValue);
//     }
//   formBody = formBody.join("&");
//
//   const response = await
//   fetch(
//     apiConfig.url+"/api/Person", {
//     crossDomain: true,
//     method: "POST",
//     headers: new Headers({
//       "Authorization": "Basic "+apiConfig.clientId,
//       "Content-Type": "application/x-www-form-urlencoded",
//       "Cache-Control": "no-cache"
//     }),
//      body: formBody
//   })
//   .then(async (response) => {
//     if(response.status === 400) {
//         this.setState({
//           errorResponse: 'Could not register, something went wrong'
//         });
//         console.log(this.state.errorResponse);
//         return;
//     }
//   })
//   .catch((error) => {
//     console.log('error');
//   })
//   .finally(() => {
//     console.log('finally');
//   });
//
// }
