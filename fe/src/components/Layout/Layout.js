import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../data/categories/api';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import Scroller from '../Scroller';

class Layout extends Component {

  constructor(props) {
    super(props)
  }

  changeCategory = (e, callback) => {
    e.preventDefault();
    const { search } = this.props.location;
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/'+ locale + '/' + currency + '/category/' + e.currentTarget.id + search);
    if(callback) { callback() }
  }

  changeBrand = (e, callback) => {
    e.preventDefault();
    const { search } = this.props.location;
    const { locale, currency, term } = this.props.match.params;
    const value = e.currentTarget.id;
    if(value === 'All') {
      this.props.history.push('/'+ locale + '/' + currency + '/category/' + term + search);
    } else {
      this.props.history.push('/'+ locale + '/' + currency + '/category/' + term + '/brand/' + value + search);
    }
    if(callback) { callback() }
  }

  render() {
    const children = React.Children.map(this.props.children, child => {
     return React.cloneElement(child, {
       categoryList: this.props.categoryList,
       changeCategory: this.changeCategory,
       changeBrand: this.changeBrand,
     });
   });
    return (
      <React.Fragment>
        <Header
          {...this.props}
        />
          {children}
        <Scroller />
        <Footer />
      </React.Fragment>
    )
  }
}


export default withRouter(Layout);
