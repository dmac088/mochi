import React from "react";
import "./App.css";
import "./assets/css/bootstrap.min.css"
import "./assets/scss/main.scss";
import "./assets/css/font-awesome.min.css";
import "./assets/css/elegent.min.css";
import "./assets/css/plugins.css";
import "./assets/css/helper.css";
import Account from "./components/Layout/Account/Account";
import Landing from "./components/Layout/Landing/Landing";
import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";
import NotFound from "./components/Layout/NotFound";
import Dashboard from "./components/CategoryTool/Dashboard";
import Contact from "./components/Layout/Contact/Contact";
import AddCapability from "./components/CategoryTool/AddCapability";
import UpdateCapability from "./components/CategoryTool/UpdateCapability";
import { Provider } from "react-redux";
import store from "./store";
import Container from "./components/Layout/Container";
import Bag from "./components/Layout/Bag/Bag";
import WishList from "./components/Layout/WishList/WishList";
import Checkout from "./components/Layout/Checkout/Checkout";
import Auth from "./components/Layout/Login/Auth";
import Products from "./components/Layout/Products/Products"

function App() {

  const renderContainer = (Component) => {
    return (
      <Container>
        <Component />
      </Container>
    );
  }

  return (
    <Provider store={store}>
      <Router>
        <Switch>
          <Route
            exact path="/:lang/:curr"
            render={() => renderContainer(Landing)} />

          <Route
            exact path="/:lang/:curr/contact"
            render={() => renderContainer(Contact)} />

          <Route
            exact path="/:lang/:curr/dashboard"
            render={() => renderContainer(Dashboard)} />

          <Route
            exact path="/:lang/:curr/addCapability"
            render={() => renderContainer(AddCapability)} />

          <Route
            exact path="/:lang/:curr/updateCapability"
            render={() => renderContainer(UpdateCapability)} />

          <Route
            exact path="/:lang/:curr/myaccount"
            render={() => renderContainer(Account)} />

          <Route
            exact path="/:lang/:curr/mybag"
            render={() => renderContainer(Bag)} />

          <Route
            exact path="/:lang/:curr/mywishlist"
            render={() => renderContainer(WishList)} />

          <Route
            exact path="/:lang/:curr/mycheckout"
            render={() => renderContainer(Checkout)} />

          <Route
            exact path="/:lang/:curr/auth"
            render={() => renderContainer(Auth)} />

          <Route
            exact path="/:lang/:curr/category/:categoryDesc"
            render={() => renderContainer(Products)} /> 

          <Redirect from="/" to="/en-GB/HKD" />

          <Route component={NotFound} />

        </Switch>
      </Router>
    </Provider>
  );
}

export default (App);