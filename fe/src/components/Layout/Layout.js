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
    console.log(this.props);
    const { locale } = this.props.match.params;
    const { updateLocale } = this.props;
    updateLocale(locale);
  }

  componentDidUpdate() {
    const { locale } = this.props.match.params;
    const { updateLocale } = this.props;
    if(!(locale === this.props.locale)) {
      updateLocale(locale);
    }
  }

  changeCategory = (e) => {
    e.preventDefault();
    const url = this.props.location.pathname;
    const search = this.props.location.search;
    const { locale, currency, term } = this.props.match.params;
    this.props.history.push('/'+ locale + '/' + currency + '/category/' + event.target.id + search);
  }

  render() {
    const children = React.Children.map(this.props.children, child => {
     return React.cloneElement(child, {
       categoryList: this.props.categoryList,
       changeCategory: this.changeCategory,
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
