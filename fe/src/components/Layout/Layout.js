import React, { Component } from 'react';
import * as categoryApi from '../../data/categories/api';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import Scroller from '../Scroller';

class Layout extends Component {

  constructor(props) {
    super(props)
  }

  componentDidMount() {
    const { locale, currency } = this.props.match.params;
    const { updateLocale } = this.props;
    updateLocale(locale, currency);
  }

  componentDidUpdate(prevProps, prevState) {
    const { locale, currency } = this.props.match.params;
    const prevLocale = prevProps.match.params.locale;
    const prevCurrency = prevProps.match.params.currency;
    const { updateLocale } = this.props;
    if(!(locale === prevLocale && currency === prevCurrency)) {
      updateLocale(locale, currency);
    }
  }

  changeCategory = (e) => {
    e.preventDefault();
    const { search } = this.props.location;
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/'+ locale + '/' + currency + '/category/' + e.currentTarget.id + search);
  }

  changeBrand = (e) => {
    e.preventDefault();
    const { search } = this.props.location;
    const { locale, currency, term } = this.props.match.params;
    const value = e.currentTarget.id;
    if(value === 'All') {
      this.props.history.push('/'+ locale + '/' + currency + '/category/' + term + search);
    } else {
      this.props.history.push('/'+ locale + '/' + currency + '/category/' + term + '/brand/' + value + search);
    }
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


export default Layout;
