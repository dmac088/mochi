import React, {Component} from 'react';

class ManageBasket extends Component {


}

export default connect(state => ({
    tokens: state.services.session.tokens,
    customer: state.services.session.customer
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokensActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
	},
}))(Login);
