import React, { Component } from 'react';
import { connect } from 'react-redux';
import {
  Route,
  Link,
  BrowserRouter as Router,
  Switch,
} from 'react-router-dom';
import store from './store';
import Header from './components/Header';
import Signup from './components/Signup';
import * as sessionService from './services/session';
import * as productService from './services/product';
import Landing from './components/Landing';
import Login from './components/Login';
import Footer from './components/Footer';
import Products from  './components/Products';
import * as sessionSelectors from './services/session/selectors';



class App extends Component {

  constructor(props) {
    super(props);
    this.state = {};
  }

  autoLogin = () =>  {
    console.log('autoLogin');
    sessionService.refreshToken().then(() => {
      console.log('the token has been refreshed');
      this.setState({ initialRoute: routeStack[0] });
    }).catch(() => {
      console.log('the token has not been refreshed');
      //this.setState({ initialRoute: routeStack[0] });
    });
  }

  // Fetch Initial Set of Products from external API
  getProducts() {
    productService.findAll('HKG');
  }


  componentDidMount() {
    this.getProducts();
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
    console.log(store.getState());
    //const {isLoading} = this.state;
    return (
        <div className="App">
          <link rel="stylesheet"
                href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
                integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
                crossOrigin="anonymous"
          />


        <Router>
          <div>
            <Header tokens={this.props.tokens}
                    customer={this.props.customer}
            />
            <Route path="/Landing" component={Landing} />
            <Route path="/Login" component={Login} />
            <Route path="/Signup" component={Signup} />
          </div>
        </Router>

        <Footer/>
        </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    //take value from reducer, alias used in combinReducers in ./data/reducer.js
    tokens: state.services.session.tokens,
    customer: state.services.customer.customer,
    products: state.services.product.product
  };
};

const routeStack = [
   { name: 'Landing', component: Landing },
   { name: 'Login',   component: Login },
   { name: 'Signup',  component: Signup },
 ];
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
