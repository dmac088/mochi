import React, { Component } from 'react';
import Products from './Products';
import { withRouter } from 'react-router-dom';

class Landing extends Component {


  componentWillMount() {
  }


  render() {
    console.log("rendering Landing");
    return(
          <Products
            productsList={this.props.pagedItems.content}
            openModal={this.props.openModal}
            lang={this.props.lang}
          />
      );
  }
}

export default withRouter(Landing);
