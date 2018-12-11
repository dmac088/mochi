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
import Landing from './components/Landing';
import Login from './components/Login';
import Footer from './components/Footer';
import * as productSelectors from './services/product/selectors';
import './scss/style.scss';


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
        term: '',
        quantity: 0
    };
  }

  autoLogin = () =>  {
    sessionService.refreshToken().then(() => {
      //this.setState({ initialRoute: routeStack[0] });
    }).catch(() => {
      //this.setState({ initialRoute: routeStack[0] });
    });
  }

  componentDidMount() {
		const unsubscribe = store.subscribe(() => {
                                              			if (store.getState().services.persist.isHydrated) {
                                              				unsubscribe(); //call unsubscribe again! wait! what!?
                                              				this.autoLogin();
                                              			}

		                                    });
		store.subscribe(this.reduxSubscribedFunction);
	}

  reduxSubscribedFunction = () => {
  }

  // Search by Keyword
  handleSearch = (event) => {
    console.log(event.target.value);
    this.setState({ term: event.target.value });
  }

  render() {
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
                    handleSearch={this.handleSearch}
            />
            <Route path="/" exact component={Landing} />
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
    products: state.services.product.items
  };
};

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
