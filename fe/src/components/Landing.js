import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as productService from '../services/product';
import * as tokenActionCreators from '../services/session/actions';
import * as customerActionCreators from '../services/customer/actions';
import * as productActionCreators from '../services/product/actions';
import Products from  './Products';

class Landing extends Component {

  constructor(props) {
    super(props);
  }

  // Fetch Initial Set of Products from external API
  getProducts() {
    productService.findAll('HKG');
  }

  componentWillMount() {
    this.getProducts();
  }

  render() {
    return(
        <div>
          <Products
            productsList={this.props.products}
            searchTerm=''
            addToCart=''
            productQuantity=''
            updateQuantity=''
            openModal=''
          />
        </div>
    );
  }
}

export default connect(state => ({
    tokens: state.services.session.tokens,
		products: state.services.product.items,
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokenActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
    product: bindActionCreators(productActionCreators, dispatch),
	},
}))(Landing);
