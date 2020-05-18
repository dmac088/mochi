import React from "react";
import "./App.css";
import "./assets/scss/main.scss";
import "./assets/css/font-awesome.min.css";
import "./assets/css/elegent.min.css";
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
        <div className="App">
          <div className="container">
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

                <Redirect from="/" to="/en-GB/HKD" />
              </Switch>
          </div>
        </div>
      </Router>
    </Provider>
  );
}

export default (App);