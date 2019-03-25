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
                 };
  }

  componentDidMount() {
    const { locale } = this.props.match.params;
    this.getCategories(locale);
  }

  getCategories = (locale) =>
    categoryApi.findAll(locale)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .then((responseJSON) => {
        this.setState({
          categoryList: responseJSON,
        })
    })
    .catch(()=>{
        console.log('getCategories failed!');
    });

  changeCategory = (e) => {
    e.preventDefault();
    const url = this.props.location.pathname;
    const search = this.props.location.search;
    const { term } = this.props.match.params;
    console.log(url);
    console.log(search);
    console.log(term);
    this.props.history.push(url.replace(term || '', event.target.id) + search);
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
