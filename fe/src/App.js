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
import ManageBasket from './components/ManageBasket';
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
       modalActive: false,
    };
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
  //console.log('handleAddToCart');
  cartService.addToCart(this.props.cart, selectedProducts);
  setTimeout(() => {
                      this.setState({
                       cartBounce: false,
                       quantity: 1,
                     });

                   },
  1000
  );
}

  emptyCart = () => {
    cartService.emptyCart();
    this.setState({
      totalItems: 0,
      totalAmount: 0,
    });
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
  //console.log('rendering app!')
  return (
   <div className="App">
    <link rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
      crossOrigin="anonymous"
    />
  <Router>
    <div>
      <Header authenticated={this.props.tokens.authenticated}
        customer={this.props.customer}
        handleSearch={this.handleSearch}
        totalItems={this.props.cart.totalItems}
        total={this.props.cart.totalAmount}

      />
      <Route path="/" exact component =  {(routeProps) => (
                  <Landing {...routeProps}
                    {...this.state}
                    addToCart={this.handleAddToCart}
                    openModal={this.openModal}
                    updateQuantity={this.updateQuantity}
                    productQuantity={this.state.quantity}
                  />
                )} />
      <Route path="/Login" component =  {(routeProps) => (
                <Login {...routeProps}
                />
      )}/>
      <Route path="/Signup" component={Signup} />
    </div>
    </Router>
    <ManageBasket/>
    <Footer/>
      <button onClick={this.printState}>Print Redux State</button>
      <button onClick={this.printProps}>Print Props</button>
      <button onClick={this.emptyCart}>Empty Cart</button>
      <button onClick={this.reviewCart}>Manage Basket</button>
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
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokensActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
    cart: bindActionCreators(cartActionCreators, dispatch),
	},
}))(App);
