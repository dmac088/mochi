import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import {
  withRouter,
  Route,
  Redirect
} from 'react-router-dom';
import store from './store';
import Header from './components/Header';
import Products from './components/Products';
import * as tokensActionCreators from './services/session/actions';
import * as customerActionCreators from './services/customer/actions';
import * as sessionService from './services/session';
import * as pageService from './services/page';
import * as cartService from './services/cart';
import * as categoryApi from './data/categories/api';
import Landing from './components/Landing';
import Login from './components/Login';
import qs from 'query-string';
import _ from 'lodash';
import './../public/assets/scss/main.scss';



class App extends Component {

  constructor(props) {
    super(props);
      this.state = {
        queryParams: {
                       lang: "en-GB",
                       category: "ALL",
                       term: "",
                       page: "0",
                       size: "10",
                       sort: "2",
                     },
                     categoryList: [],
                     modalActive: false,
                     pagedItems: {content:[]},
                     isMounted: 0,
                   };
  }

  getCategories = (lang = "en-GB", level = 1) =>
    categoryApi.findAllForLevel(lang, level)
    .then((response) => {
      return response.text();
    })
    .then((responseText) => {
      return JSON.parse(responseText);
    })
    .then((responseJSON) => {
      return responseJSON
    })
    .catch(()=>{
      console.log('getCategories failed!');
    });

  getProducts = (queryParams) =>
    pageService.findAll(queryParams.lang,
                        queryParams.category,
                        queryParams.term,
                        queryParams.page,
                        queryParams.size,
                        queryParams.sort);



  refreshData = (/*search*/) => {
    //let urlParams = (qs.parse(search));
    //let stateParams = (qs.parse(qs.stringify(this.state.queryParams)));

    //if the local state and url parameters match then no need to reload data
    //if (_.isEqual(stateParams, urlParams) && this.state.isMounted === 1) {return null;}
    //let mergedParams = Object.assign(_.cloneDeep(stateParams), urlParams);

    //let productPromise  = this.getProducts(mergedParams);
    //let categoryPromise = this.getCategories(mergedParam.lang);
    let categoryPromise = this.getCategories(this.state.lang);

    //fetch the data and set the state
    Promise.all([/*productPromise,*/categoryPromise])
    .then((values) => {
         this.setState({
                         /*queryParams:   mergedParams,
                         pagedItems:    values[0],*/
                         categoryList:  values[0],
                         isMounted: 1,
                       }, () => {
                          //also set the URL state, since they need to be in sync
                          // this.props.history.push({
                          //       "pathname": '/Search',
                          //       "search": qs.stringify(mergedParams),
                          // });


                       });
      });
  }

  componentWillMount() {
    this.refreshData();

  }

  componentDidUpdate(prevProps, prevState, snapshot) {
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
        lang={this.state.queryParams.lang}
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
        lang={this.state.queryParams.lang}
        openModal={this.openModal}
        pagedItems={this.state.pagedItems}
        categoryList={this.state.categoryList}
      />
    );
  }

  renderHeader = (routeProps) => {
    return (
      <Header
        {...routeProps}
        authenticated={this.props.tokens.authenticated}
        customer={this.props.customer}
      />
    );
  }

  renderDefault = (props) => {
    return(
      <React.Fragment>
        {this.renderHeader(props)}
        {this.renderLanding(props)}
      </React.Fragment>
    )
  }

  renderLogin = (routeProps) => {
    return (
              <Login
                {...routeProps}
              />
          );
  }

  render() {
    //console.log("render App");
    return (
          <React.Fragment>
            <Route
              path={"/:locale?"}
              exact={true}
              render={(props) => this.renderDefault(props)}
            />
            <Route
              path={"/:locale?/category/:category"}
              render={(props) =>
                <React.Fragment>
                  {this.renderHeader(props)}
                  {this.renderProducts(props)}
                </React.Fragment>
              }
            />
          </React.Fragment>
    );
  }
}


//on a dispatch call from anywhere in the application
//this function will fire and update authenticated
export default withRouter(connect(state => ({
    tokens:   state.services.session.tokens,
    customer: state.services.customer.customer
}), dispatch => ({
  	actions: {
  		tokens:   bindActionCreators(tokensActionCreators, dispatch),
      customer: bindActionCreators(customerActionCreators, dispatch)
  	},
}))(App));
