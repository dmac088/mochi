import React, { Component } from 'react';
import Products from './Products';
import { withRouter } from 'react-router-dom';
import qs from 'query-string';
import * as pageService from '../services/page';

class Landing extends Component {

  constructor(props) {
    super(props);
  }

  componentWillMount() {
  }



  render() {

    console.log("rendering Landing");
  //  console.log(this.props.location);
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
