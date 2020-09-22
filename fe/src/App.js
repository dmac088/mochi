import React from "react";
import { history } from './components/Layout/Helpers/Route/History';
import "./App.css";
import "./assets/css/bootstrap.min.css"
import "./assets/scss/main.scss";
import "./assets/css/font-awesome.min.css";
import "./assets/css/elegent.min.css";
import "./assets/css/plugins.css";
import "./assets/css/helper.css";
import Account from "./components/Layout/Account/Account";
import Landing from "./components/Layout/Landing/Landing";
import { Router, Route, Switch, Redirect } from "react-router-dom";
import NotFound from "./components/Layout/NotFound";
import Contact from "./components/Layout/Contact/Contact";
import { Provider } from "react-redux";
import store from "./store";
import Container from "./components/Layout/Container";
import Bag from "./components/Layout/Bag/Bag";
import WishList from "./components/Layout/WishList/WishList";
import Checkout from "./components/Layout/Checkout/Checkout";
import Auth from "./components/Layout/Login/Auth";
import Forgot from "./components/Layout/Login/Forgot";
import Products from "./components/Layout/Products/Products";
import Product from "./components/Layout/Products/Product/Product";


function App() { 

  const renderContainer = (Component, props) => {
    return (
      <Container
        {...props}>
        <Component 
          {...props}/>
      </Container>
    );
  }
  
  return (
    <Provider store={store}>
      <Router history={history}>
        <Switch>
          <Route
            exact path="/:lang/:curr"
            render={(props) => renderContainer(Landing, props)} />

          <Route
            exact path="/:lang/:curr/contact"
            render={(props) => renderContainer(Contact, props)} />

          <Route
            path="/:lang/:curr/myaccount"
            render={(props) => renderContainer(Account, props)} />

          <Route
            exact path="/:lang/:curr/mybag"
            render={(props) => renderContainer(Bag, props)} />

          <Route
            exact path="/:lang/:curr/mywishlist"
            render={(props) => renderContainer(WishList, props)} />

          <Route
            exact path="/:lang/:curr/mycheckout"
            render={(props) => renderContainer(Checkout, props)} />

          <Route
            exact path="/:lang/:curr/auth"
            render={(props) => renderContainer(Auth, props)} />

          <Route
            exact path="/:lang/:curr/forgot"
            render={(props) => renderContainer(Forgot, props)} />     

          <Route
            exact path="/:lang/:curr/category/:categoryCode"
            render={(props) => renderContainer(Products, props)} /> 

          <Route
            exact path="/:lang/:curr/:type/category/:categoryCode"
            render={(props) => renderContainer(Products, props)} />   

          <Route
            exact path="/:lang/:curr/category/:categoryCode/product/:productCode"
            render={(props) => renderContainer(Product, props)} />   

          <Redirect from="/" to="/en-GB/HKD" />

          <Route component={NotFound} />

        </Switch>
      </Router>
    </Provider>
  );
}

export default (App);