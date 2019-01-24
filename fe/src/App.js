import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import {
  Route,
  withRouter
} from 'react-router-dom';
import store from './store';
import Header from './components/Header';
import Signup from './components/Signup';
import ManageCart from './components/ManageCart';
import Paginator from './components/Paginator';
import PageSize from './components/PageSize';
import PageSort from './components/PageSort';
import * as tokensActionCreators from './services/session/actions';
import * as customerActionCreators from './services/customer/actions';
import * as cartActionCreators from './services/cart/actions';
import * as sessionService from './services/session';
import * as pageService from './services/page';
import * as cartService from './services/cart';
import * as categoryApi from './data/categories/api';
import Landing from './components/Landing';
import Login from './components/Login';
import Footer from './components/Footer';
import './scss/style.scss';
import CategoryNavigator from './components/CategoryNavigator'
import langSelector from './config/lang/selector';
import qs from 'query-string';
import _ from 'lodash';



class App extends Component {

  constructor(props) {
    super(props);
      this.state = {
        queryParams: {
                       lang: "ENG",
                       category: "ALL",
                       term: "",
                       page: "0",
                       size: "10",
                       sort: "2",
                     },
                     categoryList: [],
                     modalActive: false,
                     pagedItems: {content:[]}
                   };
  }

  getCategories = (lang = "ENG") =>
    categoryApi.findAll(lang)
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



  refreshData = (search) => {
    let urlParams = (qs.parse(search));
    let stateParams = (qs.parse(qs.stringify(this.state.queryParams)));
    //if the local state and url parameters match then no need to reload data
    if (_.isEqual(stateParams, urlParams)) {return null;}
    let mergedParams = Object.assign(stateParams, urlParams);
    //define our promises and fetch the data
    let productPromise  = this.getProducts(mergedParams);
    let categoryPromise = this.getCategories(mergedParams.lang);

    //fetch the data and set the state
    Promise.all([productPromise,categoryPromise])
    .then((values) => {
         this.setState({
                         queryParams:   mergedParams,
                         pagedItems:    values[0],
                         categoryList:  values[1]
                       }, () => {
                          //also set the URL state, since they need to be in sync
                          this.props.history.push({
                                "pathname": '/Search',
                                "search": qs.stringify(this.state.queryParams),
                          });
                       });
      });
  }

  componentWillMount() {
    this.refreshData(this.props.location.search);
  }

  componentDidUpdate() {
    this.refreshData(this.props.location.search);
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
      />
    );
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
   <div className="App">
      <div>
        <div className="row">
          <div className="col-sm-12">
            <Header authenticated={this.props.tokens.authenticated}
                    customer={this.props.customer}
                    updateSearch={this.updateSearch}
                    changeLang={this.changeLang}
                    lang={this.state.queryParams.lang}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-sm-2">
            <CategoryNavigator
              categoryList={this.state.categoryList}
              refreshData={this.refreshData}
              lang={this.state.queryParams.lang}
            />
          </div>
          <div className="col-sm-10">
            <PageSize
              size={this.state.queryParams.size}
            />
            <PageSort currentPageSort
              currentPageSort={this.state.currentPageSort}
              changePageSort={this.changePageSort}
            />
            <br/>
            <Paginator
              totalPages={this.state.pagedItems.totalPages}
            />
            <Route path="/" exact={true} component={this.renderLanding}/>
            <Route path="/Login" component ={this.renderLogin}/>
            <Route path="/Signup" component={Signup} />
            <Route path="/Search" component={this.renderLanding}
            />
          </div>
        </div>
      </div>
      <ManageCart/>
      <Footer/>
      <button onClick={this.printState}>Print Redux State</button>
      <button onClick={this.printLocalState}>Print Local State</button>
      <button onClick={this.printProps}>Print Props</button>
      <button onClick={this.emptyCart}>Empty Cart</button>
   </div>
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
