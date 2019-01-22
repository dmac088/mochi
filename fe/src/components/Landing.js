import React, { Component } from 'react';
import Products from './Products';
import { withRouter } from 'react-router-dom';
import qs from 'query-string';
import * as pageService from '../services/page';

class Landing extends Component {

  constructor(props) {
    super(props);
      this.state = {
        page: {content:[]}
      };
  }

  componentWillMount() {
    const values = (qs.parse(this.props.location.search));
    const queryObject =       {
                        lang:     values.lang,
                        category: values.category,
                        term:     values.term,
                        page:     values.page,
                        size:     values.size,
                        sort:     values.sort,
                      };
    this.getProducts(queryObject.lang, queryObject.category, queryObject.term, queryObject.page, queryObject.size, queryObject.sort);
	}

  getProducts = (lang = "ENG", category = "ALL", term = "", page = 0, size = 5, sort = 2) => {
    pageService.findAll(lang,
                        category,
                        (term === undefined || term === "") ? "-Z" : term,
                        page,
                        size,
                        sort)
    .then((response) => {
       this.setState((prevState) => ({
         page: response,
       }));
    })
    .then(() => {
      console.log(this.state.currentPage);
    });
  }

  render() {
    return(
          <Products
            productsList={this.state.page.content}
            addToCart={this.props.addToCart}
            openModal={this.props.openModal}
            currentLang={this.props.currentLang}
          />
      );
  }
}

export default withRouter(Landing);
