import React, { Component } from 'react';
import Products from './Products';
import { withRouter } from 'react-router-dom';
import qs from 'query-string';
import * as pageService from '../services/page';

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
                      category: "ALL",
                      lang: "ENG",
                      page: 0,
                      size: 10,
                      sort: 2,
                      term: "",
                    };
    const searchString = qs.stringify(defaultQs);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
    this.getProducts();
	}

  getProducts = () => {
    const values = (qs.parse(this.props.location.search));
    //console.log(values.category)
    pageService.findAll(values.lang,
                        values.category,
                        (values.term === undefined || values.term === "") ? "-Z" : values.term,
                        values.page,
                        values.size,
                        values.sort)
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
    console.log(this.props.location.search);
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
