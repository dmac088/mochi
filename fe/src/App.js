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
import * as productApi from './data/products/api';
import Layout from './components/Layout/Layout';
import LayoutBC from './components/Layout/LayoutBC';
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
                     "locale": null,
                     "currency": "USD",
                     "categoryList": [],
                     "showQVModal": false,
                     "currentProductId": null,
                     "featuredProducts": [],
                  };
  }

  componentDidMount() {
    // const unsubscribe = store.subscribe(() => {
    //                     			if (store.getState().services.persist.isHydrated) {
    //                     				unsubscribe(); //call
    //                     				this.autoLogin();
    //                     			}
    //                     });
  }

  updateLocale = (locale) => {
    if(locale === this.state.locale) {return;}
    this.refreshData(locale);
  }

  refreshData(locale) {
    Promise.all([this.refreshCategoryList(locale),
                 this.refreshFeaturedProducts(locale)])
    .then((values) => {
      this.setState({
        "locale": locale,
        "categoryList": values[0],
        "featuredProducts": values[1].content,
      });
    });
  }

  refreshFeaturedProducts = (locale) =>
    productApi.findByCategory(locale, 'Featured', 0, 50)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .then((responseJSON) => {
        return responseJSON;
    });


  refreshCategoryList = (locale) =>
    categoryApi.findAll(locale)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .then((responseJSON) => {
        return responseJSON;
    });

  autoLogin = () =>  {
    sessionService.refreshToken().then(() => {
   //this.setState({ initialRoute: routeStack[0] });
    }).catch(() => {
   //move to error
   //this.setState({ initialRoute: routeStack[0] });
    });
  }

  toggleQuickView = (e) => {
    e.preventDefault();
    this.setState({
      "showQVModal": !this.state.showQVModal,
    });
  }

  setCurrentProductId = (e) => {
    e.preventDefault();
    this.setState({
      "currentProductId": e.currentTarget.id,
      "showQVModal": true,
    });
  }

  emptyCart = () => {
    cartService.emptyCart();
  }

  renderLayout = (routeProps, contentCallback) => {
    const { categoryList } = this.state;
    return (
      <Layout {...routeProps}
        locale={this.state.locale}
        categoryList={categoryList}
        updateLocale={this.updateLocale}>
          {contentCallback(routeProps)}
      </Layout>
    );
  }

  renderLayoutBC = (routeProps, contentCallback) => {
    const { categoryList } = this.state;
    return (
      <LayoutBC {...routeProps}
          locale={this.state.locale}
          categoryList={categoryList}
          updateLocale={this.updateLocale}>
          {contentCallback(routeProps)}
      </LayoutBC>
    );
  }

  renderLanding = (routeProps) => {
    const { locale, currency, currentProductId, showQVModal, featuredProducts } = this.state;
    return (
        <Landing
          {...routeProps}
          locale={locale}
          currency={currency}
          showQVModal={showQVModal}
          setCurrentProductId={this.setCurrentProductId}
          currentProductId={currentProductId}
          toggleQuickView={this.toggleQuickView}
          featuredProducts={featuredProducts}
        />
    );
  }

  renderProducts = (routeProps) => {
    const { locale, currency, pagedItems, categoryList, showQVModal, currentProductId } = this.state;
    return (
        <Products
          {...routeProps}
          toggleQuickView={this.toggleQuickView}
          showQVModal={showQVModal}
          setCurrentProductId={this.setCurrentProductId}
          currentProductId={currentProductId}
          categoryList={categoryList}
          page="Products"
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
        page="Checkout"
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
        page="Cart"
      />
    );
  }

  renderAuth = (routeProps) => {
    const { tokens, customer } = this.props;
      return(
         (!tokens.authenticated)
      ?  (<Auth {...routeProps} />)
      :  (<Account
                  {...routeProps}
                  authenticated={tokens.authenticated}
                  customer={customer}
                />)
      )
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
          <Route path={"/:locale/:currency"} exact={true}                     render={(props)   => this.renderLayout(props, this.renderLanding)}     />
          <Route path={"/:locale/:currency/(category|search)/:term/product/:productId"} exact={true}  render={(props)   => this.renderLayoutBC(props, this.renderProduct)}     />
          <Route path={"/:locale/:currency/(category|search)/:term"}          render={(props)   => this.renderLayoutBC(props, this.renderProducts)}    />
          <Route path={"/:locale/:currency/Checkout"} exact={true}            render={(props)   => this.renderLayoutBC(props, this.renderCheckout)}    />
          <Route path={"/:locale/:currency/Cart"} exact={true}                render={(props)   => this.renderLayoutBC(props, this.renderCart)}        />
          <Route path={"/:locale/:currency/Account"} exact={true}             render={(props)   => this.renderLayoutBC(props, this.renderAuth)}        />
          <Route path={"/:locale/:currency/Wishlist"} exact={true}            render={(props)   => this.renderLayoutBC(props, this.renderWishlist)}    />
          <Route path={"/:locale/:currency/Contact"} exact={true}             render={(props)   => this.renderLayoutBC(props, this.renderContact)}     />
          <Route path={"/:locale/:currency/Auth"} exact={true}                render={(props)   => this.renderLayoutBC(props, this.renderAuth)}        />

          <Redirect from="/" to="/en-GB/HKD" />
          <Route                                                              render={(props)   => this.renderLayout(props, this.renderLanding)}     />
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
