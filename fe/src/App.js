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
import { getValue } from './config/lang/selector';


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
                     "locale": null,
                     "currency": "USD",
                     "categoryList": [],
                     "showQVModal": false,
                     "currentProductId": null,
                     "landingCategories": [],
                     "previewCategories": [],
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

  //we cache data in App to avoid refreshing on route changes (componentDidMount)
  refreshData(locale) {
    this.refreshCategoryList(locale)
    .then((categoryList) => {
      this.setState({
        "locale": locale,
        "categoryList": categoryList,
      });
    })
    .then(() => {
      const { categoryList } = this.state;
      //return an array of promises to the next in chain
      return this.filterLandingCategories(categoryList).map(category => {
        //we must return the nested promise
        return this.getCategoryProducts(locale, category.categoryDesc)
        .then((response) => {
          category["products"] = response;
          return category;
        });
      });
    })
    .then((promiseArray) => {
      Promise.all(promiseArray)
      .then((value) => {
        this.setState({
          "landingCategories": value,
        });
      });
    })
    .then(() => {
      const { categoryList } = this.state;
      //return an array of promises to the next in chain
      return this.filterPreviewCategories(categoryList).map(category => {
        //we must return the nested promise
        return this.getCategoryProducts(locale, category.categoryDesc)
        .then((response) => {
          category["products"] = response;
          return category;
        });
      });
    })
    .then((promiseArray) => {
      Promise.all(promiseArray)
      .then((value) => {
        this.setState({
          "previewCategories": value,
        });
      });
    })
    .catch((e) => {
      console.log(e);
    });
  }

  getCategoryProducts = (locale, category) =>
    productApi.findByCategory(locale, category, 0, 50)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .then((responseJSON) => {
        return responseJSON.content;
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

  filterLandingCategories = (categoryList) => {
    return categoryList.filter(function(value, index, arr){
      return value.landingDisplay === 1;
    });
  }


  filterPreviewCategories = (categoryList) => {
    return categoryList.filter(function(value, index, arr){
      return value.categoryPreview === 1;
    });
  }

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
    const { locale, currency, categoryList } = this.state;
    return (
      <Layout {...routeProps}
        locale={locale}
        currency={currency}
        categoryList={categoryList}
        updateLocale={this.updateLocale}>
          {contentCallback(routeProps)}
      </Layout>
    );
  }

  renderLayoutBC = (routeProps, contentCallback) => {
    const { locale, currency, categoryList } = this.state;
    return (
      <LayoutBC {...routeProps}
        locale={locale}
        currency={currency}
        categoryList={categoryList}
        updateLocale={this.updateLocale}>
        {contentCallback(routeProps)}
      </LayoutBC>
    );
  }

  renderLanding = (routeProps) => {
    const { locale, currency, currentProductId, showQVModal, landingCategories, previewCategories } = this.state;
    return (
      <Landing
        {...routeProps}
        locale={locale}
        currency={currency}
        showQVModal={showQVModal}
        setCurrentProductId={this.setCurrentProductId}
        currentProductId={currentProductId}
        toggleQuickView={this.toggleQuickView}
        landingCategories={landingCategories}
        previewCategories={previewCategories}
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
          <Route path={"/:locale/:currency"} exact={true}                                             render={(props)   => this.renderLayout(props, this.renderLanding)}        />
          <Route path={"/:locale/:currency/(category|search)/:term/product/:productId"} exact={true}  render={(props)   => this.renderLayoutBC(props, this.renderProduct)}      />
          <Route path={"/:locale/:currency/(category|search)/:term"}                                  render={(props)   => this.renderLayoutBC(props, this.renderProducts)}     />
          <Route path={"/:locale/:currency/Checkout"} exact={true}                                    render={(props)   => this.renderLayoutBC(props, this.renderCheckout)}     />
          <Route path={"/:locale/:currency/Cart"} exact={true}                                        render={(props)   => this.renderLayoutBC(props, this.renderCart)}         />
          <Route path={"/:locale/:currency/Account"} exact={true}                                     render={(props)   => this.renderLayoutBC(props, this.renderAuth)}         />
          <Route path={"/:locale/:currency/Wishlist"} exact={true}                                    render={(props)   => this.renderLayoutBC(props, this.renderWishlist)}     />
          <Route path={"/:locale/:currency/Contact"} exact={true}                                     render={(props)   => this.renderLayoutBC(props, this.renderContact)}      />
          <Route path={"/:locale/:currency/Auth"} exact={true}                                        render={(props)   => this.renderLayoutBC(props, this.renderAuth)}         />

          <Redirect from="/" to="/en-GB/HKD" />
          <Route                                                                                      render={(props)   => this.renderLayout(props, this.renderLanding)}        />
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
