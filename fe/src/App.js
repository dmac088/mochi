import React from "react";
import "./App.css";
import "./assets/scss/main.scss";
import "./assets/css/font-awesome.min.css";
import "./assets/css/elegent.min.css";
import Header from "./components/Layout/Header/Header";
import Footer from "./components/Layout/Footer/Footer";
import Account from "./components/Layout/Account/Account";
import Landing from "./components/Layout/Landing";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import NotFound from "./components/Layout/NotFound";
import Dashboard from "./components/CategoryTool/Dashboard";
import Contact from "./components/Layout/Contact/Contact";
import AddCapability from "./components/CategoryTool/AddCapability";
import UpdateCapability from "./components/CategoryTool/UpdateCapability";
import { Provider } from "react-redux";
import store from "./store";

function App() {

    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            <div className="container">
              {
                //The <Switch> will iterate over its children elements (the routes) and only render the first one that matches the current pathname.
                /*
              if you dont put switch, it will all render together inclusively
              */
              }
              <Switch>
                <Route exact path="/" component={Landing} />
                <Route
                  exact path="/dashboard"
                  component={Dashboard}
                />
                <Route 
                  exact path="/addCapability" 
                  component={AddCapability} />
                <Route
                  exact path="/updateCapability"
                  component={UpdateCapability}
                />
                <Route
                  exact path="/contact"
                  component={Contact}
                />
                <Route
                  exact path="/myaccount"
                  component={Account}
                />
                <Route component={NotFound} />
              </Switch>
            </div>
            <Footer />
          </div>
        </Router>
      </Provider>
    );
}

export default (App);