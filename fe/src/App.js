import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import {
  Route,
  BrowserRouter as Router,
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



class App extends Component {

  constructor(props) {
    super(props);
      this.state = {
       currentCategory: "All",
       currentLang: "ENG",
       currentPage: 0,
       currentPageSize: 10,
       currentPageSort: 2,
       page: {content:[]},
       categoryList: [],
       currentSearchTerm: "",
       modalActive: false,
    };
  }

  getCategories(lang) {
    categoryApi.findAll(lang)
    .then((response) => {
      return response.text();
    })
    .then((responseText) => {
      return JSON.parse(responseText);
    })
    .then((responseJSON) => {
      this.setState({
        categoryList: responseJSON
      });
    })
    .catch(()=>{
      console.log('getCategories failed!');
    });
  }

  // Fetch Initial Set of Products from external API
  getProducts = (locale = "ENG", category = 'All', searchTerm = "Fruit", page = 0, size = 10, sort = 2) => {
    pageService.findAll(locale, category, searchTerm, page, size, sort)
    .then((response) => {
       this.setState((prevState) => ({
         page: response,
         currentCategory: category,
         currentLang: locale,
         currentSearchTerm: searchTerm,
         currentPage: (prevState.currentCategory !== category) ? 0 : page,
         currentPageSize: size,
         currentPageSort: sort,
       }));
    })
    .then(() => {
      console.log(this.state.currentPage);
    });
  }

  handleSearch = (event) => {
    console.log(event);
    this.getProducts(this.state.currentLang, this.state.currentCategory, this.state.currentSearchTerm, this.state.currentPage, this.state.currentPageSize, this.state.currentPageSort);
  }

  changeLang = (event) => {
    this.getProducts(event.target.id, this.state.currentCategory, this.state.searchTerm, this.state.currentPage, this.state.currentPageSize, this.state.currentPageSort);
  }

  changeCategory = (event) => {
    this.getProducts(this.state.currentLang, event.target.id, this.state.searchTerm, this.state.currentPage, this.state.currentPageSize, this.state.currentPageSort);
  }

  changePage = (event) => {
    this.getProducts(this.state.currentLang, this.state.currentCategory, this.state.searchTerm, event.target.id, this.state.currentPageSize, this.state.currentPageSort);
  }

  changePageSize = (event) => {
    this.getProducts(this.state.currentLang, this.state.currentCategory, this.state.searchTerm, this.state.currentPage, event.target.id, this.state.currentPageSort);
  }

  changePageSort = (event) => {
    this.getProducts(this.state.currentLang, this.state.currentCategory, this.state.searchTerm, this.state.currentPage, this.state.currentPageSize, event.target.id);
  }

  componentWillMount() {
    this.getCategories(this.state.currentLang);
    this.getProducts(this.state.currentLang, this.state.currentCategory, this.state.searchTerm, this.state.currentPage, this.state.currentPageSize, this.state.currentPageSort);
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
		store.subscribe(this.reduxSubscribedFunction);
	}

  reduxSubscribedFunction = () => {
  }

  updateSearch = (event) => {
    console.log(event.target.value);
    this.setState({
      currentSearchTerm: event.target.value
    });
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

  render() {
  return (
   <div className="App">
    <Router>
      <div>
        <div className="row">
          <div className="col-sm-12">
            <Header authenticated={this.props.tokens.authenticated}
                    customer={this.props.customer}
                    handleSearch={this.handleSearch}
                    updateSearch={this.updateSearch}
                    changeLang={this.changeLang}
                    lang={langSelector[this.state.currentLang]}
                    currentLang={this.state.currentLang}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-sm-2">
            <CategoryNavigator
              categoryList={this.state.categoryList}
              changeCategory={this.changeCategory}
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
              page={this.state.page}
              changePage={this.changePage}
            />
            <Route path="/" exact component =  {(routeProps) => (
                                                                    <Landing {...routeProps}
                                                                      {...this.state}
                                                                      lang={langSelector[this.state.currentLang]}
                                                                      openModal={this.openModal}
                                                                      updateQuantity={this.updateQuantity}
                                                                      productQuantity={this.state.quantity}
                                                                    />
                                                                )}
            />
            <Route path="/Login" component =  {(routeProps) => (
                      <Login {...routeProps}
                      />
            )}/>
            <Route path="/Signup" component={Signup} />
          </div>
        </div>
      </div>
      </Router>

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
export default connect(state => ({
    tokens:   state.services.session.tokens,
    customer: state.services.customer.customer
}), dispatch => ({
  	actions: {
  		tokens:   bindActionCreators(tokensActionCreators, dispatch),
      customer: bindActionCreators(customerActionCreators, dispatch)
  	},
}))(App);
