import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as tokenActionCreators from '../services/session/actions';
import * as customerActionCreators from '../services/customer/actions';

class Landing extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return(
        <div>
          <p>Landing Page</p>
        </div>
    );
  }
}

export default connect(state => ({
    tokens: state.services.session.tokens,
		//routeHistory: state.services.routeHistory,
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokenActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
	},
}))(Landing);
