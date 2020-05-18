import React from "react";
import "./App.css";
import "./assets/scss/main.scss";
import "./assets/css/font-awesome.min.css";
import "./assets/css/elegent.min.css";
import Footer from "./components/Layout/Footer/Footer";
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

function App() {


  function renderContainer(Component) {
    return(
      <Container>
         <Component />
      </Container>
    );
  }

    return (
      <Provider store={store}>
        <Router>
         
          <div className="App">
            {/*we must wrap the header in a Route in order to access the props of router, alternatively we can use withRouter HOC*/}
            {/* <Route 
                  path="/:lang/:curr" 
                  component={Header}/>
          */}
            <div className="container">
              {
                //The <Switch> will iterate over its children elements (the routes) and only render the first one that matches the current pathname.
                /*
              if you dont put switch, it will all render together inclusively
              */
              }
              <Container>
                <Switch>
                  <Route 
                    exact path="/:lang/:curr" 
                    render={() => renderContainer(Landing)}/>

                  <Route 
                    exact path="/:lang/:curr/contact" 
                    render={() => renderContainer(Contact)}/>
                  
                  <Route
                    exact path="/:lang/:curr/dashboard"
                    render={() => renderContainer(Dashboard)}/>

                  <Route 
                    exact path="/:lang/:curr/addCapability" 
                    component={() => renderContainer(AddCapability)} />

                  <Route 
                    exact path="/:lang/:curr/updateCapability" 
                    component={() => renderContainer(UpdateCapability)} />

                  <Route
                    exact path="/:lang/:curr/myaccount"
                    component={() => renderContainer(Account)}
                  /> */}

                  <Redirect from="/" to="/en-GB/HKD" />
                  
                </Switch>
              </Container>
            </div>
            <Footer />
          </div>
        </Router>
      </Provider>
    );
}

export default (App);