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
import Header from './components/Header/Header';
import Products from './components/Products/Products';
import * as tokensActionCreators from './services/session/actions';
import * as customerActionCreators from './services/customer/actions';
import * as sessionService from './services/session';
import * as pageService from './services/page';
import * as cartService from './services/cart';
import * as categoryApi from './data/categories/api';
import Landing from './components/Landing/Landing';
import Checkout from './components/Checkout/Checkout';
import Cart from './components/Cart/Cart';
import Account from './components/Account/Account';
import Wishlist from './components/Wishlist/Wishlist';
import Contact from './components/Contact/Contact';
import Product from './components/Product/Product';
import Auth from './components/Login/Auth';
import qs from 'query-string';
import _ from 'lodash';
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

  getProducts = (queryParams) =>
    pageService.findAll(queryParams.locale,
                        queryParams.category,
                        queryParams.term,
                        queryParams.page,
                        queryParams.size,
                        queryParams.sort);



  refreshData = (prevState) => {
    //let urlParams = (qs.parse(search));
    //let stateParams = (qs.parse(qs.stringify(this.state.queryParams)));

    //if the local state and url parameters match then no need to reload data
    //if (_.isEqual(stateParams, urlParams) && this.state.isMounted === 1) {return null;}
    //let mergedParams = Object.assign(_.cloneDeep(stateParams), urlParams);

    //let productPromise  = this.getProducts(mergedParams);
    //let categoryPromise = this.getCategories(mergedParam.locale);
    //let categoryPromise = this.getCategories(this.state.locale);

    //fetch the data and set the state
    // Promise.all([/*productPromise,*/categoryPromise])
    // .then((values) => {
    //      this.setState({
    //                      /*queryParams:   mergedParams,
    //                      pagedItems:    values[0],*/
    //                      categoryList:  values[0],
    //                      isMounted: 1,
    //                    }, () => {
    //                       //also set the URL state, since they need to be in sync
    //                       // this.props.history.push({
    //                       //       "pathname": '/Search',
    //                       //       "search": qs.stringify(mergedParams),
    //                       // });
    //
    //
    //                    });
    //   });
  }

  componentWillMount(prevProps, prevState, snapshot) {
    // if(this.props.match.params.locale === undefined) {
    //   this.props.history.push('/' + this.state.locale  + "/" + this.state.currency);
    // }
    this.refreshData(prevState);
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    //return;
    ///this.refreshData(prevState);
    //if(!this.shouldRefreshdata(this.props.location)) {return(null);}
    /*this.refreshData(this.props.location.search);*/
  }

  shouldRefreshdata = (location) => {
    return (location.pathname === "/Search"
         || location.pathname === "/");
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
    //console.log("componentDidMount");
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

  emptyCart = () => {
    cartService.emptyCart();
  }

  printState = () => {
    console.log(store.getState());
  }

  printLocalState = () => {
    console.log(this.state);
  }

  printProps = () => {
    console.log(this.props);
  }

  renderLanding = (routeProps) => {
    return (
      <Landing
        {...routeProps}
        locale={this.state.locale}
        currency={this.state.currency}
        openModal={this.openModal}
        pagedItems={this.state.pagedItems}
        categoryList={this.state.categoryList}
      />
    );
  }

  renderProducts = (routeProps) => {
    return (
      <Products
        {...routeProps}
        locale={this.state.locale}
        currency={this.state.currency}
        openModal={this.openModal}
        pagedItems={this.state.pagedItems}
        categoryList={this.state.categoryList}
      />
    );
  }

  renderHeader = () => {
    return (
      <Header
        authenticated={this.props.tokens.authenticated}
        customer={this.props.customer}
      />
    );
  }

  renderCheckout = (routeProps) => {
    return (
      <Checkout
        {...routeProps}
        authenticated={this.props.tokens.authenticated}
        customer={this.props.customer}
      />
    );
  }

  renderCart = (routeProps) => {
    return (
      <Cart
        {...routeProps}
        authenticated={this.props.tokens.authenticated}
        customer={this.props.customer}
        cart={this.props.cart}
      />
    );
  }

  renderAccount = (routeProps) => {
    return (
      <Account
        {...routeProps}
        authenticated={this.props.tokens.authenticated}
        customer={this.props.customer}
      />
    );
  }

  renderAuth = (routeProps) => {
    return (
              <Auth
                {...routeProps}
              />
          );
  }

  renderWishlist = (routeProps) => {
    return (
              <Wishlist
                {...routeProps}
              />
          );
  }

  renderContact = (routeProps) => {
    return (
              <Contact
                {...routeProps}
              />
          );
  }

  renderProduct = (routeProps) => {
    return (
              <Product
                {...routeProps}
              />
          );
  }


  render() {
    //console.log("render App");
    return (
          <React.Fragment>
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
          </React.Fragment>
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
