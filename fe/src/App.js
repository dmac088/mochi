import React, { Component } from 'react';
import { connect } from 'react-redux';
import apiConfig from './config/config';
import store from './store';
import Header from './components/Header';
import Signup from './components/Signup';
import * as session from './services/session';
import * as api from './services/api';
import * as usersApi from './data/users/api';
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
    this.state = initialStateApp();
  }

  componentDidMount() {
    console.log('componentDidMount');
    console.log(this.state);
		// Waits for the redux store to be populated with the previously saved state,
		// then it will try to auto-login the user.
		store.subscribe(this.reduxSubscribedFunction);
	}

  reduxSubscribedFunction = () => {
    console.log('subscribed function triggered');
  }

  render() {
    //const {isLoading} = this.state;
    return (
        <div className="App">
          <link rel="stylesheet"
                href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
                integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
                crossOrigin="anonymous"
          />
        <Header tokens={this.props.tokens}/>
        <Signup/>
        </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    //take value from reducer, alias used in combinReducers in ./data/reducer.js
    tokens: state.services.session.tokens
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


const initialStateApp = () => {
  return  JSON.parse('{"isLoading": false,  "error": null,"username": "","password": "","firstName": ""}');
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
