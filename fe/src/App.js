import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import {
  withRouter,
  Route,
  Redirect,
  Switch,
} from 'react-router-dom';
import { matchPath } from "react-router";
import store from './store';
import * as tokensActionCreators from './services/session/actions';
import * as customerActionCreators from './services/customer/actions';
import * as sessionService from './services/session';
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
                     "currency": "HKD",
                     "categoryList": [],
                     "showQVModal": false,
                     "currentProductId": null,
                     "landingCategories": [],
                     "previewCategories": [],
                  };
  }

  componentDidMount() {
    const unsubscribe = store.subscribe(() => {
                        			if (store.getState().services.persist.isHydrated) {
                        				unsubscribe(); //call
                        				this.autoLogin();
                        			}
                        });
  }

  componentDidUpdate(prevProps, prevState) {
    console.log("componentDidUpdate");
    const prevLocation = prevProps.location;
    const prevParams = matchPath(prevLocation.pathname, {path:'/:locale/:currency', exact: true,strict: false,}).params;
    const prevLocale = prevParams.locale;
    const prevCurrency = prevParams.currency;
    const { location } = this.props;
    const { locale, currency } = matchPath(location.pathname, {path:'/:locale/:currency', exact: true,strict: false,}).params;
    console.log(prevParams);
    console.log(matchPath(location.pathname, {path:'/:locale/:currency', exact: true,strict: false,}).params);
  }

  updateLocale = (locale, currency) => {
    if(locale === this.state.locale
       && currency === this.state.currency) {return;}
    this.refreshData(locale, currency);
  }

  //we cache data in App to avoid refreshing on route changes (componentDidMount)
  refreshData(locale, currency) {
    this.refreshCategoryList(locale, currency)
    .then((categoryList) => {
      this.setState({
        "locale": locale,
        "currency": currency,
        "categoryList": categoryList,
      });
    })
    .then(() => {
      const { categoryList } = this.state;
      //return an array of promises to the next in chain
      return this.filterLandingCategories(categoryList).map(category => {
        //we must return the nested promise
        return this.getCategoryProducts(locale, currency, category.categoryDesc)
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
        return this.getCategoryProducts(locale, currency ,category.categoryDesc)
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

  getCategoryProducts = (locale, currency, category) =>
    productApi.findByCategory(locale, currency, category, "", 500, 0, 50)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .then((responseJSON) => {
        return responseJSON.content;
    });


  refreshCategoryList = (locale, currency) =>
    categoryApi.findAll(locale, currency)
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


  renderLayout = (contentCallback) => {
    const { locale, currency, categoryList } = this.state;
    return (
      <Layout
        locale={locale}
        currency={currency}
        categoryList={categoryList}
        updateLocale={this.updateLocale}>
          {contentCallback()}
      </Layout>
    );
  }

  renderLayoutBC = (contentCallback) => {
    const { locale, currency, categoryList } = this.state;
    return (
      <LayoutBC
        locale={locale}
        currency={currency}
        categoryList={categoryList}
        updateLocale={this.updateLocale}>
        {contentCallback()}
      </LayoutBC>
    );
  }

  renderLanding = (routeProps) => {
    const { locale, currency, currentProductId, showQVModal, landingCategories, previewCategories } = this.state;
    return (
      <Landing
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

  renderProducts = () => {
    const { locale, currency, pagedItems, categoryList, showQVModal, currentProductId } = this.state;
    return (
      <Products
        toggleQuickView={this.toggleQuickView}
        showQVModal={showQVModal}
        setCurrentProductId={this.setCurrentProductId}
        currentProductId={currentProductId}
        categoryList={categoryList}
      />
    );
  }

  renderCheckout = () => {
    const { tokens, customer } = this.props;
    return (
      <Checkout
        authenticated={tokens.authenticated}
        customer={customer}
        page="Checkout"
      />
    );
  }

  renderCart = () => {
    const { tokens, customer, cart } = this.props;
    return (
      <Cart
        authenticated={tokens.authenticated}
        customer={customer}
        cart={cart}
        page="Cart"
      />
    );
  }

  renderAuth = () => {
    const { tokens, customer } = this.props;
      return(
         (!tokens.authenticated)
      ?  (<Auth
            page={"Account"}
          />)
      :  (<Account
                  authenticated={tokens.authenticated}
                  customer={customer}
                  page={"Account"}
                />)
      )
  }

  renderWishlist = () => {
    return (<Wishlist
              page={"Wishlist"}
            />);
  }

  renderContact = () => {
    return (<Contact />);
  }

  renderProduct = () => {
    return (<Product />);
  }


  render() {
    return (
        <Switch>
          <Route path={"/:locale/:currency"} exact={true}                                             render={()   => this.renderLayout(this.renderLanding)}        />
          <Route path={"/:locale/:currency/(category|search)/:term/product/:productId"} exact={true}  render={()   => this.renderLayoutBC(this.renderProduct)}      />
          <Route path={"/:locale/:currency/(category|search)/:term/brand/:brand"}                     render={()   => this.renderLayoutBC(this.renderProducts)}     />
          <Route path={"/:locale/:currency/(category|search)/:term"}                                  render={()   => this.renderLayoutBC(this.renderProducts)}     />
          <Route path={"/:locale/:currency/(search)"}                                                 render={()   => this.renderLayoutBC(this.renderProducts)}     />
          <Route path={"/:locale/:currency/Checkout"} exact={true}                                    render={()   => this.renderLayoutBC(this.renderCheckout)}     />
          <Route path={"/:locale/:currency/Cart"} exact={true}                                        render={()   => this.renderLayoutBC(this.renderCart)}         />
          <Route path={"/:locale/:currency/Account"} exact={true}                                     render={()   => this.renderLayoutBC(this.renderAuth)}         />
          <Route path={"/:locale/:currency/Wishlist"} exact={true}                                    render={()   => this.renderLayoutBC(this.renderWishlist)}     />
          <Route path={"/:locale/:currency/Contact"} exact={true}                                     render={()   => this.renderLayoutBC(this.renderContact)}      />
          <Route path={"/:locale/:currency/Auth"} exact={true}                                        render={()   => this.renderLayoutBC(this.renderAuth)}         />
          <Redirect from="/" to="/en-GB/HKD" />
          <Route                                                                                      render={(props)   => this.renderLayout(this.renderLanding)}   />
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
