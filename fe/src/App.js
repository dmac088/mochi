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
import Landing from './components/Landing';
import Login from './components/Login';
import Footer from './components/Footer';
import * as productSelectors from './services/product/selectors';
import './scss/style.scss';


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
        items: [],
        cart: [],
        searchTerm: '',
        quantity: 0,
        modalActive: false,
    };
  }

  // Fetch Initial Set of Products from external API
  getProducts() {
    productService.findAll('HKG')
    .then((response) => {
      this.setState({
        items: response
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

  checkProduct = (productID) => {
     let cart = this.state.cart;
     return cart.some(function(item) {
       return item.id === productID;
     });
   }

   sumTotalItems = () => {
     let total = 0;
     let cart = this.state.cart;
     total = cart.length;
     this.setState({
       totalItems: total
     });
   }

   sumTotalAmount = () => {
   let total = 0;
   let cart = this.state.cart;
   for (var i = 0; i < cart.length; i++) {
     total += cart[i].price * parseInt(cart[i].quantity);
   }
   this.setState({
     totalAmount: total
   });
 }

  // Add to Cart
 handleAddToCart = (selectedProducts) => {
  console.log('handleAddToCart');
  let cartItem = this.state.cart;
  let productID = selectedProducts.id;
  let productQty = selectedProducts.quantity;
  if (this.checkProduct(productID)) {
    //console.log("hi");
    let index = cartItem.findIndex(x => x.id == productID);
    cartItem[index].quantity =
      Number(cartItem[index].quantity) + Number(productQty);
    this.setState({
      cart: cartItem
    });
  } else {
    cartItem.push(selectedProducts);
  }
  this.setState({
    cart: cartItem,
    cartBounce: true
  });
  setTimeout(
    function() {
      this.setState({
        cartBounce: false,
        quantity: 1
      });
      console.log(this.state.quantity);
      console.log(this.state.cart);
    }.bind(this),
    1000
  );
  this.sumTotalItems(this.state.cart);
  this.sumTotalAmount(this.state.cart);
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
                                                  />
                                                )} />
              <Route path="/Login" component={Login} />
              <Route path="/Signup" component={Signup} />
            </div>
          </Router>
          <Footer/>
        </div>
    );
  }
}


//on a dispatch call from anywhere in the application
//this function will fire and update authenticated
export default connect(state => ({
  tokens:   state.services.session.tokens,
  customer: state.services.customer.customer,
  cart:     state.services.cart.cart,
		//routeHistory: state.services.routeHistory,
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokensActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
    cart: bindActionCreators(cartActionCreators, dispatch),
	},
}))(App);
