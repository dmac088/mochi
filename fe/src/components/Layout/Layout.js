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
    const { locale } = this.props.match.params;
    const { updateLocale } = this.props;
    updateLocale(locale);
  }

  componentDidUpdate(prevProps, prevState) {
    const { locale } = this.props.match.params;
    const prevlocale = prevProps.match.params.locale;
    const { updateLocale } = this.props;
    if(!(locale === prevlocale)) {
      updateLocale(locale);
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
    this.props.history.push('/'+ locale + '/' + currency + '/category/' + term + '/brand/' + e.currentTarget.id + search);
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
