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
import * as cartService from './services/cart';
import * as cartSelector from './services/cart/selectors';
import * as categoryApi from './data/categories/api';
import * as productApi from './data/products/api';
import { Layout } from './components/Layout/Layout';
import { LayoutBC } from './components/Layout/LayoutBC';
import { Landing } from './components/Landing/Landing';
import Products from './components/Products/Products';
import { Checkout } from './components/Checkout/Checkout';
import { Cart } from './components/Cart/Cart';
import { Account } from './components/Account/Account';
import { Wishlist } from './components/Wishlist/Wishlist';
import { Contact } from './components/Contact/Contact';
import Product from './components/Product/Product';
import { Auth } from './components/Login/Auth';
import './../public/assets/scss/main.scss';
import { getValue } from './config/lang/selector';
import { filterCategories } from './services/helpers/filterHelper';


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
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
                                              				unsubscribe();
                                              				this.autoLogin()
                                                      .then(() => {
                                                        console.log("autologin worked, refreshing access token with found refresh token");
                                                      })
                                                      .catch((e) => {
                                                        console.log("autologin didn't work, no refresh token found on the client!");

                                                      })
                                                      .then(() => {
                                                        this.initialize();
                                                      });
                                              			}
                                              });
  }

  initialize = () => {
    const match = matchPath(this.props.location.pathname, {path:'/:locale/:currency', exact: false, strict: false,});
    if(!match) { return }
    const { locale, currency } = match.params;
    this.refreshData(locale, currency);
  }

  componentDidUpdate(prevProps, prevState) {
    const prevMatch = matchPath(prevProps.location.pathname, {path:'/:locale/:currency', exact: false, strict: false,});
    const match = matchPath(this.props.location.pathname, {path:'/:locale/:currency', exact: false, strict: false,});
    const prevParams = (prevMatch) ? prevMatch.params : null;
    const prevLocale = (prevLocale) ? prevParams.locale : null;
    const { locale, currency } = match.params;
    if(prevParams
      && locale === prevParams.locale
       && currency === prevParams.currency) {return;}
    this.refreshData(locale, currency);
  }

  //we cache data in App to avoid refreshing on route changes (componentDidMount)
  refreshData(locale, currency) {
    this.refreshCategoryList(locale, currency)
    .then((categoryList) => {
      this.setState({
        "categoryList": categoryList,
      });
    })
    .then(() => {
      const { categoryList } = this.state;
      //return an array of promises to the next in chain
      return filterCategories(categoryList, 'LNDHC01').map(category => {
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
      return filterCategories(categoryList, 'LNDPC01').map(category => {
        //we must return the nested promise
        return this.getCategoryProducts(locale, currency, category.categoryDesc)
        .then((response) => {
          category["products"] = response;
          return category;
        });
      });
    })
    .then(() => {
      //update the cart products in redux store
      const productIds = cartSelector.get().items.map(a => a.productId);
      //console.log(productIds);
      //call the rest Api to apply new item array based on new language
      productApi.findByIds(locale, currency, productIds)
      .then((response) => {
        return response.text()
      })
      .then((responseText) => {
        return JSON.parse(responseText);
      })
      .then((responseJSON) => {
        cartService.updateCartItems(responseJSON);
      })
      .then(() => {
        cartService.updateCartTotals();
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
        return responseJSON.products.content;
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


  filterPreviewCategories = (categoryList) => {
    return categoryList.filter(function(value, index, arr){
      return value.categoryPreview === 1;
    });
  }

  autoLogin = () =>
    sessionService.refreshToken();


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
    const { categoryList } = this.state;
    return (
      <Layout
        categoryList={categoryList}>
          {contentCallback()}
      </Layout>
    );
  }

  renderLayoutBC = (contentCallback) => {
    const { categoryList } = this.state;
    return (
      <LayoutBC
        categoryList={categoryList}>
        {contentCallback()}
      </LayoutBC>
    );
  }

  renderLanding = (routeProps) => {
    const { currentProductId, showQVModal, landingCategories, previewCategories } = this.state;
    return (
      <Landing
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
    const { pagedItems, categoryList, showQVModal, currentProductId } = this.state;
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
    return (<Contact
              page={"Contact"}
            />);
  }

  renderProduct = () => {
    return (<Product />);
  }


  render() {
    return (
        <Switch>
          <Route path={"/:locale/:currency"}          exact={true}                                    render={()   => this.renderLayout(this.renderLanding)}        />
          <Route path={"/:locale/:currency/(category|search)/:term/product/:productId"}               exact={true}  render={()   => this.renderLayoutBC(this.renderProduct)} />
          <Route path={"/:locale/:currency/product/:productId"}                                       exact={true}  render={()   => this.renderLayoutBC(this.renderProduct)} />
          <Route path={"/:locale/:currency/(category|search)/:term/brand/:brand"}                     render={()   => this.renderLayoutBC(this.renderProducts)}     />
          <Route path={"/:locale/:currency/(category|search)/:term"}                                  render={()   => this.renderLayoutBC(this.renderProducts)}     />
          <Route path={"/:locale/:currency/(search)"}                                                 render={()   => this.renderLayoutBC(this.renderProducts)}     />
          <Route path={"/:locale/:currency/Checkout"} exact={true}                                    render={()   => this.renderLayoutBC(this.renderCheckout)}     />
          <Route path={"/:locale/:currency/Cart"}     exact={true}                                    render={()   => this.renderLayoutBC(this.renderCart)}         />
          <Route path={"/:locale/:currency/Account"}  exact={true}                                    render={()   => this.renderLayoutBC(this.renderAuth)}         />
          <Route path={"/:locale/:currency/Wishlist"} exact={true}                                    render={()   => this.renderLayoutBC(this.renderWishlist)}     />
          <Route path={"/:locale/:currency/Contact"}  exact={true}                                    render={()   => this.renderLayoutBC(this.renderContact)}      />
          <Route path={"/:locale/:currency/Auth"}     exact={true}                                    render={()   => this.renderLayoutBC(this.renderAuth)}         />
          <Redirect from="/" to="/en-GB/HKD" />
          <Route                                                                                      render={()   => this.renderLayout(this.renderLanding)}   />
        </Switch>
    );
  }
}

//on a dispatch call from anywhere in the application
//this function will fire and update authenticated
export default withRouter(connect(state => ({
    cart:     state.services.cart,
    tokens:   state.services.session.tokens,
    customer: state.services.customer.customer
}), dispatch => ({
  	actions: {
  		tokens:   bindActionCreators(tokensActionCreators, dispatch),
      customer: bindActionCreators(customerActionCreators, dispatch)
  	},
}))(App));
