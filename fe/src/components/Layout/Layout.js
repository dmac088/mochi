import React, { Component } from 'react';
import * as categoryApi from '../../data/categories/api';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import Scroller from '../Scroller';

class Layout extends Component {

  constructor(props) {
    super(props)
    this.state = {
                  "categoryList": [],
                  "locale": "en-GB"
                 };
  }

  componentDidMount() {
    const { locale } = this.props.match.params;
    this.refreshData(locale, 1);
  }

  componentDidUpdate() {
    const { locale } = this.props.match.params;
    this.refreshData(locale, 0);
  }

  refreshData = (locale, isMounting) => {
    if(locale === this.state.locale
      && isMounting === 0) {return;}
    categoryApi.findAll(locale)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .then((responseJSON) => {
        this.setState({
          "categoryList": responseJSON,
          "locale": locale
        })
    })
    .catch(()=>{
        console.log('getCategories failed!');
    });
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
       categoryList: this.state.categoryList,
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
