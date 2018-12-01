import React, { Component } from 'react';
import { connect } from 'react-redux';
import store from './store';
import Header from './components/Header';
import Signup from './components/Signup';



class App extends Component {

  constructor(props) {
    super(props);
    this.state = {};
  }

  componentDidMount() {
    console.log('componentDidMount');
    console.log(this.state);
		store.subscribe(this.reduxSubscribedFunction);
	}

  reduxSubscribedFunction = () => {
    console.log('subscribed function triggered');
  }

  render() {
    //const {isLoading} = this.state;
    return (
        <div className="App">
          <link rel="stylesheet"
                href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
                integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
                crossOrigin="anonymous"
          />
        <Header tokens={this.props.tokens}/>
        <Signup/>
        </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    //take value from reducer, alias used in combinReducers in ./data/reducer.js
    tokens: state.services.session.tokens
  };
};

//on a dispatch call from anywhere in the application
//this function will fire and update authenticated
const mapDispatchToProps = (dispatch) => {
  return {
    authenticated: (value) => {
      dispatch({
        type: "UPDATE",
        payload: value
      })
    }
  };
};




export default connect(mapStateToProps,mapDispatchToProps)(App);
