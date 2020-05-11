import React, { Component } from "react";
import "./App.css";
import Header from "./components/Layout/Header";
import Landing from "./components/Layout/Landing";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import NotFound from "./components/Layout/NotFound";
import Dashboard from "./components/CapabilityTool/Dashboard";
import AddCapability from "./components/CapabilityTool/AddCapability";
import UpdateCapability from "./components/CapabilityTool/UpdateCapability";
import { connect } from "react-redux";
import { getDiscovery } from "./actions/DiscoveryActions";

class App extends Component {

  componentDidMount() {
    this.props.getDiscovery();
  }

  render() {
    const discovery = { ...this.props }

    return (
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
                  render={() => <Dashboard {...discovery} />}
                />
                <Route exact path="/addCapability" component={AddCapability} />
                <Route
                  exact
                  path="/updateCapability"
                  component={UpdateCapability}
                />
                <Route component={NotFound} />
              </Switch>
            </div>
          </div>
        </Router>
    );
  }
}

const mapStateToProps = state => ({
  discovery: state.discovery.links,
})

export default connect(mapStateToProps,
                      {getDiscovery})
                      (App);
