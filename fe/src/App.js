import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import {
  withRouter,
  Route,
  Redirect,
  Switch,
} from 'react-router-dom';
import store from './store';
import * as tokensActionCreators from './services/session/actions';
import * as customerActionCreators from './services/customer/actions';
import * as sessionService from './services/session';
import * as pageService from './services/page';
import * as cartService from './services/cart';
import * as categoryApi from './data/categories/api';
import Landing from './components/Landing/Landing';
import Products from './components/Products/Products';
import Checkout from './components/Checkout/Checkout';
import Cart from './components/Cart/Cart';
import Account from './components/Account/Account';
import Wishlist from './components/Wishlist/Wishlist';
import Contact from './components/Contact/Contact';
import Product from './components/Product/Product';
import Auth from './components/Login/Auth';
import './../public/assets/scss/main.scss';



class App extends Component {

  constructor(props) {
    super(props);
      this.state = {
                     locale: "en-GB",
                     currency: "USD",
                     categoryList: [],
                     modalActive: false,
                     pagedItems: {content:[]},
                     isMounted: 0,
                   };
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
	}

  emptyCart = () => {
    cartService.emptyCart();
  }

  renderLanding = (routeProps) => {
    const { locale, currency, pagedItems, categoryList } = this.state;
    return (
      <Landing
        {...routeProps}
        locale={locale}
        currency={currency}
        pagedItems={pagedItems}
        categoryList={categoryList}
      />
    );
  }

  renderProducts = (routeProps) => {
    const { locale, currency, pagedItems, categoryList } = this.state;
    return (
      <Products
        {...routeProps}
      />
    );
  }

  renderCheckout = (routeProps) => {
    const { tokens, customer } = this.props;
    return (
      <Checkout
        {...routeProps}
        authenticated={tokens.authenticated}
        customer={customer}
      />
    );
  }

  renderCart = (routeProps) => {
    const { tokens, customer, cart } = this.props;
    return (
      <Cart
        {...routeProps}
        authenticated={tokens.authenticated}
        customer={customer}
        cart={cart}
      />
    );
  }

  renderAccount = (routeProps) => {
    const { tokens, customer } = this.props;
    return (
      <Account
        {...routeProps}
        authenticated={tokens.authenticated}
        customer={customer}
      />
    );
  }

  renderAuth = (routeProps) => {
    return (<Auth {...routeProps} />);
  }

  renderWishlist = (routeProps) => {
    return (<Wishlist {...routeProps} />);
  }

  renderContact = (routeProps) => {
    return (<Contact {...routeProps} />);
  }

  renderProduct = (routeProps) => {
    return (<Product {...routeProps} />);
  }


  render() {
    return (
      <Switch>
        <Route path={"/:locale/:currency"} exact={true} render={(props)                     => this.renderLanding(props)}     />
        <Route path={"/:locale/:currency/(Category|Search)/:term"} render={(props)          => this.renderProducts(props)}    />
        <Route path={"/:locale/:currency/Checkout"} exact={true} render={(props)            => this.renderCheckout(props)}    />
        <Route path={"/:locale/:currency/Cart"} exact={true} render={(props)                => this.renderCart(props)}        />
        <Route path={"/:locale/:currency/Account"} exact={true} render={(props)             => this.renderAccount(props)}     />
        <Route path={"/:locale/:currency/Wishlist"} exact={true} render={(props)            => this.renderWishlist(props)}    />
        <Route path={"/:locale/:currency/Contact"} exact={true} render={(props)             => this.renderContact(props)}     />
        <Route path={"/:locale/:currency/Auth"} exact={true} render={(props)                => this.renderAuth(props)}        />
        <Route path={"/:locale/:currency/Product/:productId"} exact={true} render={(props)  => this.renderProduct(props)}     />
        <Redirect from="/" to="/en-GB/HKD" />
        <Route render={(props)                                                              => this.renderLanding(props)}     />
      </Switch>
    );
  }
}


//on a dispatch call from anywhere in the application
//this function will fire and update authenticated
export default withRouter(connect(state => ({
    cart: state.services.cart,
    tokens:   state.services.session.tokens,
    customer: state.services.customer.customer
}), dispatch => ({
  	actions: {
  		tokens:   bindActionCreators(tokensActionCreators, dispatch),
      customer: bindActionCreators(customerActionCreators, dispatch)
  	},
}))(App));
