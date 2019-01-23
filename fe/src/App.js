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



class App extends Component {

  constructor(props) {
    super(props);
      this.state = {
        queryParams: {
                       lang: "ENG",
                       category: "ALL",
                       term: "-Z",
                       page: 0,
                       size: 10,
                       sort: 2,
                     },
                     categoryList: [],
                     modalActive: false,
                     pagedItems: {content:[]},
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

  getProducts = (lang = "ENG", category = "ALL", term = "", page = 0, size = 10, sort = 2) =>
    pageService.findAll(lang,
                        category,
                        (term === undefined || term === "") ? "-Z" : term,
                        page,
                        size,
                        sort);



  //this function will read URL parameters
  //and refresh the product data accordingly via setState
  //child compoents that change the URL params need to
  //explicitly call this function to re-render the refreshData
  //which will update state and force a refresh of child components
  refreshData = () => {
    let params = (qs.parse(this.props.location.search));
    let productPromise = this.getProducts(
                                           params.lang,
                                           params.category,
                                           params.term,
                                           params.page,
                                           params.size,
                                           params.sort
                                         );

    let categoryPromise = this.getCategories(params.lang);

    Promise.all(
                [productPromise,
                categoryPromise]
               )
    .then((values) => {
         this.setState({
                         queryParams: {
                                        lang:      (params.lang === undefined) ? "ENG" : params.lang,
                                        category:  (params.category === undefined) ? "ALL" : params.category,
                                        term:      (params.term === undefined || params.term === "") ? "-Z" : params.term,
                                        page:      (params.page === undefined) ? 0 : params.page,
                                        size:      (params.size === undefined) ? 10 : params.size,
                                        sort:      (params.sort === undefined) ? 2 : params.sort,
                                      },
                                      pagedItems: values[0],
                                      categoryList: values[1]
                       });
       });
  }

  componentWillMount() {
    console.log("componentWillMount");
    this.refreshData();
  }

  componentDidUpdate = (prevProps, prevState) => {
    console.log("componentDidUpdate");
    //this.refreshData();
  }

  componentWillReceiveProps() {
    console.log("componentWillReceiveProps");
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
    console.log("componentDidMount");
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

  // Open Modal
  openModal = (product) => {
    // this.setState({
    //   quickViewProduct: product,
    //   modalActive: true
    // });
  }

  // Close Modal
  closeModal = () => {
    // this.setState({
    //   modalActive: false
    // });
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

  render() {
    console.log("render App");
  return (
   <div className="App">
      <div>
        <div className="row">
          <div className="col-sm-12">
            <Header authenticated={this.props.tokens.authenticated}
                    customer={this.props.customer}
                    handleSearch={this.handleSearch}
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
              currentPageSize={this.state.currentPageSize}
              changePageSize={this.changePageSize}
            />
            <PageSort currentPageSort
                currentPageSort={this.state.currentPageSort}
                changePageSort={this.changePageSort}

            />
            <br/>
            <Paginator
              pagedItems={this.state.pagedItems}
              changePage={this.changePage}
            />
            <Route path="/" exact={true} component =  {this.renderLanding}/>
            <Route path="/Login" component =  {(routeProps) => (
                                                                <Login
                                                                  {...routeProps}
                                                                />
                                              )}/>
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
