import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Route, Link, BrowserRouter as Router } from 'react-router-dom';
import store from './store';
import Header from './components/Header';
import Signup from './components/Signup';
import * as session from './services/session';
import Landing from './components/Landing';
import Login from './components/Login';


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {};
  }

  autoLogin = () =>  {
    console.log('autoLogin');
    session.refreshToken().then(() => {
      console.log('the token has been refreshed');
    //	this.setState({ initialRoute: routeStack[3] });
    }).catch(() => {
      console.log('the token has not been refreshed');
    //	this.setState({ initialRoute: routeStack[0] });
    });
  }

  componentDidMount() {
    // Waits for the redux store to be populated with the previously saved state,
		// then it will try to auto-login the user.
		const unsubscribe = store.subscribe(() => {
                                              			if (store.getState().services.persist.isHydrated) {
                                                      console.log('The store is hydrated!');
                                              				unsubscribe(); //call unsubscribe again! wait! what!?
                                              				this.autoLogin();
                                              			}

		                                          });
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
        <Header tokens={this.props.tokens}
                customer={this.props.customer}
        />
        <Router>
          <div>
            <ul>
              <li>
                <Link to="/Landing">Home</Link>
              </li>
              <li>
                <Link to="/Login">Login</Link>
              </li>
              <li>
                <Link to="/Signup">Signup</Link>
              </li>
            </ul>
            <Route path="/Landing" component={Landing} />
            <Route path="/Login" component={Login} />
            <Route path="/Signup" component={Signup} />
          </div>
        </Router>
        </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    //take value from reducer, alias used in combinReducers in ./data/reducer.js
    tokens: state.services.session.tokens,
    customer: state.services.customer.customer
  };
};

// const routeStack = [
//   { name: 'Signup', component: Signup },
//   { name: 'Welcome', component: Welcome },
// ];
//on a dispatch call from anywhere in the application
//this function will fire and update authenticated
const mapDispatchToProps = (dispatch) => {
  return {
    authenticated: (value) => {
      dispatch({
        type: "UPDATE",
        payload: value
      })
    }
  };
};

export default connect(mapStateToProps,mapDispatchToProps)(App);
