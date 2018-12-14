import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import {
  Route,
  Link,
  BrowserRouter as Router,
  Switch,
} from 'react-router-dom';
import store from './store';
import Header from './components/Header';
import Signup from './components/Signup';
import * as tokensActionCreators from './services/session/actions';
import * as customerActionCreators from './services/customer/actions';
import * as cartActionCreators from './services/cart/actions';
import * as sessionService from './services/session';
import * as productService from './services/product';
import * as cartService from './services/cart';
import Landing from './components/Landing';
import Login from './components/Login';
import Footer from './components/Footer';
import * as cartSelector from './services/cart/selectors';
import { initialState } from './services/cart/reducer';
import './scss/style.scss';


class App extends Component {

  constructor(props) {
    super(props);
      this.state = {
       productList: [],
       searchTerm: '',
       quantity: 1,
       modalActive: false,
    };
    console.log(this.state);
  }

  // Fetch Initial Set of Products from external API
  getProducts() {
    productService.findAll('HKG')
    .then((response) => {
       this.setState({
       productList: response
       });
    });
  }

  componentWillMount() {
    this.getProducts();
  }

  autoLogin = () =>  {
    sessionService.refreshToken().then(() => {
   //this.setState({ initialRoute: routeStack[0] });
  }).catch(() => {
   //move to error
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
   this.setState({ searchTerm: event.target.value });
  }

  // Add to Cart
 handleAddToCart = (selectedProducts) => {
  console.log('handleAddToCart');
  cartService.addToCart(this.props.cart, selectedProducts);
  this.setState({
    cartBounce: true
  });
  setTimeout(
    function() {
     this.setState({
       cartBounce: false,
       quantity: 1
     });
    }.bind(this),
  1000
  );
  this.setState({
    totalItems: cartService.sumTotalItems(this.props.cart),
    totalAmount: cartService.sumTotalAmount(this.props.cart)
  });
}

  emptyCart = () => {
    console.log('emptyCart....');
    cartService.persistCart({items:[]});
  }

  // Open Modal
  openModal = (product) => {
  this.setState({
    quickViewProduct: product,
    modalActive: true
  });
  }

  // Close Modal
  closeModal = () => {
  this.setState({
    modalActive: false
  });
  }

  printState = () => {
  console.log(store.getState());
  }

  printProps = () => {
  console.log(this.props);
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
      <Route path="/" exact component =  {(routeProps) => (
                  <Landing  {...routeProps}
                    {...this.state}
                    addToCart={this.handleAddToCart}
                    openModal={this.openModal}
                    updateQuantity={this.updateQuantity}
                    productQuantity={this.state.quantity}
                  />
                )} />
      <Route path="/Login" component={Login} />
      <Route path="/Signup" component={Signup} />
    </div>
    </Router>
    <Footer/>
      <button onClick={this.printState}>Print Redux State</button>
      <button onClick={this.printProps}>Print Props</button>
      <button onClick={this.emptyCart}>Empty Cart</button>
   </div>
  );
  }
}


//on a dispatch call from anywhere in the application
//this function will fire and update authenticated
export default connect(state => ({
  tokens:  state.services.session.tokens,
  customer: state.services.customer.customer,
  cart:  state.services.cart.cart,
		//routeHistory: state.services.routeHistory,
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokensActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
    cart: bindActionCreators(cartActionCreators, dispatch),
	},
}))(App);
