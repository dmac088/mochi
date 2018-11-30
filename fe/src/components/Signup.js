import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as customersApi from '../data/customers/api';
import * as session from '../services/session';
import * as customerService from '../services/customer';
import * as api from '../services/api';
import store from '../store';
import * as selectors from '../services/customer/selectors';
import { deepValue } from '../services/api';
//signup should have it's own local state and not be bound to App.js

class Signup extends Component {

  constructor(props) {
    super(props);
    this.state = {
                    customer: {
                          userName: null,
                          password: null,
                          givenName: null,
                          familyName: null
                        }
                };
    store.subscribe(this.reduxSubscribedFunction);
  }

  reduxSubscribedFunction = () => {
    console.log('signup subscribed function triggered');
  }

  updateCustomerState = (event) =>  {
    let newstate = {...this.state};
    deepValue(newstate, event.target.id, event.target.value);
    this.setState(newstate);
    console.log(this.state.customer);
  }

  signupClick = (event) => {
    console.log("signup click");
    this.setState({
      isLoading: true,
      error: '',
    });

    //const { givenNameEn, familyNameEn, userName, password } = this.state;
    //create is a CRUD operation therefore we don't need to use the service class

    //craete is not taking the initialstate from redux customerServicei
    //instead this is local state, which is not what we want
    //customersApi.create(this.state.customer)
    customerService.createNewCustomer(this.state.customer)
      .then(() => {
          //return session.authenticate(this.state.customer.userName,
            //                 this.state.customer.password);
      }).then((response) => {
        //we are using local state here since
        //our input text boxes write to local state and then
        //persist directly to database to create a new customer, not to redux (yet)
        //we poplulare reduct by making another call to the api
        //either using the userName in local state or returned party id in responseText
        //for this we use the /services/customer/index.js which must be imported
        //we need the access token from the redux store to make the subsequen API calls
        //this can be gotten by

    //  console.log(this.props.user);
    //  console.log(this.props.customer);
    //  console.log(this.props.tokens);

      //console.log(customerService.findByuserName(this.state.customer.partyUser.userName,
      //                                             this.state.customer.partyUser.password));

      }).then(() => {

        //we can reset local state, no impact to global redux state
        //const routeStack = this.props.navigator.getCurrentRoutes();
        //this.props.navigator.jumpTo(routeStack[3]);

    })
    .catch((exception) => {
      // Displays only the first error message
      const error = api.exceptionExtractError(exception);
      const newState = {
        isLoading: false,
        ...(error ? { error } : {}),
      };
        this.setState(newState);
    });
  }

  render() {
      return(
        <div className="row">
          <div className="col-md-3 order-md-1">
          </div>
        <div className="col-md-6 order-md-2">
          <h4 className="mb-3">
          Sign Up
          </h4>
          <div className="needs-validation" noValidate>
            <div className="row">
              <div className="col-md-6 mb-3">
                <label htmlFor="givenName">
                First name
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="customer.givenName"
                  onChange={this.updateCustomerState}
                  placeholder="First Name"
                  required />
                <div className="invalid-feedback">
                  Valid first name is required.
                </div>
              </div>
              <div className="col-md-6 mb-3">
                <label htmlFor="familyName">
                Last name
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="customer.familyName"
                  onChange={this.updateCustomerState}
                  placeholder="Last Name"
                  required />
                <div className="invalid-feedback">
                  Valid last name is required.
                </div>
              </div>
            </div>
            <div className="mb-3">
              <label htmlFor="userName">userName</label>
              <div className="input-group">
                <input
                  type="text"
                  className="form-control"
                  id="customer.userName"
                  onChange={this.updateCustomerState}
                  placeholder="you@placeholder.com"
                  required />
                                  <div
                  className="invalid-feedback"
                  style={{width: '100%'}}>
                  Your userName is required.
                </div>
              </div>
            </div>
            <div className="mb-3">
              <label htmlFor="passwordField">
                Password <span className="text-muted"/>
              </label>
              <input
                type="password"
                className="form-control"
                id="customer.password"
                onChange={this.updateCustomerState}
                placeholder="Password" />
            </div>

            <hr className="mb-4" />

            <button
              onClick={this.signupClick}
            className="btn btn-primary btn-lg btn-block">
            Submit
            </button>
        </div>
        <div className="col-md-3 order-md-3">
        </div>
      </div>
      <p>{'authenticated = ' + this.props.tokens.authenticated + ' '} </p>
      <br/>
      <p>{' userName = ' + this.props.tokens.userName + ' '} </p>
      <p>{' access token = ' + this.props.tokens.access_token + ' '} </p>
    </div>
      );
  }
}

const mapStateToProps = (state) => {
//  console.log("the state is .....");
//  console.log(state);
  return {

    //take value from reducer, alias used in combineReducers in ./data/reducer.js
    //state is not local state it is the parameter (state)
    tokens: state.services.session.tokens,
    user: state.services.session.user,
    customer: state.services.session.customer
  };
};

const mapDispatchToProps = (dispatch) => {
  console.log(dispatch);
  return {

    //take value from reducer, alias used in combinReducers in ./data/reducer.js
    setAuthenticated: (auth) => {
      dispatch({
        type: "SET_AUTH",
        payload: auth
      })
    }
  };
};

export default connect(mapStateToProps,mapDispatchToProps)(Signup);
