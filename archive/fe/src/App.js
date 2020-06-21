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
import * as brandApi from './data/brands/api';
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


export class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
                     "productCategoryList": [],
                     "brandCategoryList": [],
                     "showQVModal": false,
                     "currentProductId": null,
                     "landingCategories": [],
                     "previewCategories": [],
                     "selectedFacets": { "brandCategories": [],
                                         "productCategories": [],
                                         "brands": [],
                                         "tags": [],
                                         "prices": []},
                 };
  }

  componentDidMount() {
    const unsubscribe = store.subscribe(() => {
                                              			if (store.getState().services.persist.isHydrated) {
                                                      console.log("hydrated = " + store.getState().services.persist.isHydrated);
                                              				unsubscribe();
                                              				this.autoLogin()
                                                      .then(() => {
                                                        console.log("autologin worked, refreshing access token with found refresh token");
                                                      })
                                                      .catch((e) => {
                                                        console.log("autologin didn't work, no refresh token found on the client!");
                                                        console.log(e);
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

    const p1 = this.refreshCategoryList(locale, currency)
                      .then((responseJSON) => {
                        return responseJSON;
                      });

    //highlighted categories
    const p2 = (categoryList) =>
                        //return an array of promises to the next in chain
                        filterCategories(categoryList, 'LNDHC01').map(c => {
                          //we must return the nested promise
                            return this.getCategoryProducts(locale, currency, c.facetDisplayValue)
                            .then((response) => {
                              c["products"] = response;
                              return c;
                            });
                          });


    //preview categories
    const p3 = (categoryList) =>
                      //return an array of promises to the next in chain
                      filterCategories(categoryList, 'LNDPC01').map(c => {
                        //we must return the nested promise
                        return this.getCategoryProducts(locale, currency, c.facetDisplayValue)
                        .then((response) => {
                          c["products"] = response;
                          return c;
                        });
                      });


    //brand categories, get a list of brands for brand category and add to payload
    const p4 = (categoryList) =>
                        //return an array of promises to the next in chain
                        filterCategories(categoryList, 'LNDHM01').map(c => {
                        //we must return the nested promise
                          return this.getCategoryBrands(locale, currency, c.payload.categoryCode)
                          .then((response) => {
                            c.payload["brands"] = response.result.brands;
                            return c;
                          });
                        });

    //products in cart
    const p5 = (productIds) => productApi.findByIds(locale, currency, productIds)
                .then((response) => {
                  return response.json();
                });


    p1.then((response) => {
      return response.result;
    })
    .then((result) => {
      return Promise.all([            Promise.all(p2(result.productCategories)) /*langing categories*/,
                                      Promise.all(p3(result.productCategories)) /*preview categories*/,
                                      Promise.all(p4(result.brandCategories)) /*brand categories*/,
                                      p5(cartSelector.get().items.map(a => a.productUPC)),
                                      p1 /*all categories*/])
    })
    .then((response) => {
      this.setState({
        "productCategoryList":  response[4].result.productCategories,
        "brandCategoryList":    response[4].result.brandCategories,
        "landingCategories":    response[0],
        "previewCategories":    response[1],
      });
      cartService.updateCartItems(response[3]);
      cartService.updateCartTotals();
    });
  }

  getCategoryProducts = (locale, currency, category) =>
      productApi.getMaxPrice(locale, currency, category, this.state.selectedFacets)
              .then((response) => {
                 return response.json();
              })
              .then((price) => {
                return productApi.findByCategory(locale, currency, category, "", price, 0, 50)
                                    .then((response) => {
                                      return response.json();
                                    })
                                    .then((responseJSON) => {
                                        return responseJSON.products.content;
                                    })
                                    .catch((e)=>{
                                       console.log(e);
                                    });
              })
              .catch((e) => {
                console.log(e);
              });

  getCategoryBrands = (locale, currency, category) =>
      brandApi.findByBrandCategory(locale, currency, category)
              .then((response) => {
                  return response.json();
              })
              .catch((e) => {
                  console.log(e);
              });

  refreshCategoryList = (locale, currency) =>
    categoryApi.findAll(locale, currency)
    .then((response) => {
        return response.json();
    });

  filterPreviewCategories = (productCategoryList) => {
    return productCategoryList.filter(function(value, index, arr){
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
    const { productCategoryList, brandCategoryList } = this.state;
    return (
      <Layout
        categoryList={productCategoryList}
        brandCategoryList={brandCategoryList}>
          {contentCallback()}
      </Layout>
    );
  }

  renderLayoutBC = (contentCallback) => {
    const { productCategoryList, brandCategoryList } = this.state;
    return (
      <LayoutBC
        categoryList={productCategoryList}
        brandCategoryList={brandCategoryList}>
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
    const { pagedItems, productCategoryList, showQVModal, currentProductId } = this.state;
    return (
      <Products
        toggleQuickView={this.toggleQuickView}
        showQVModal={showQVModal}
        setCurrentProductId={this.setCurrentProductId}
        currentProductId={currentProductId}
        categoryList={productCategoryList}
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

  getHello = () => {
    return "Hello World!";
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
