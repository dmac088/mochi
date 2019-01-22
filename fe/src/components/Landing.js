import React, { Component } from 'react';
import Products from './Products';
import { withRouter } from 'react-router-dom';
import qs from 'query-string';

class Landing extends Component {

  constructor(props) {
    super();
    super(props);
      this.state = {
       page: {content:[]}
    };
  }

  componentWillMount() {
    const defaultQs = {
                      currentCategory: "ALL",
                      currentLang: "ENG",
                      currentPage: 0,
                      currentPageSize: 10,
                      currentPageSort: 2,
                      currentSearchTerm: "-Z",
                    };
    const searchString = qs.stringify(defaultQs);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
	}

  render() {
    console.log(this.props.location.search);
    return(
          <Products
            productsList={this.props.page.content}
            addToCart={this.props.addToCart}
            openModal={this.props.openModal}
            currentLang={this.props.currentLang}
          />
      );
  }
}

export default withRouter(Landing);
