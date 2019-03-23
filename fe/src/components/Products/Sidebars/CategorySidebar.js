import React, { Component } from 'react';
import * as categoryApi from '../../../data/categories/api';

class CategorySidebar extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "category": null,
    };
  }

  componentDidMount() {
    const { locale, term } = this.props.match.params;
    this.getCategories(locale, term)
    .then((responseJSON) => {
      this.setState({
        "category": responseJSON,
      });
    });
    // this.updateMenu(locale, 1);
    // this.renderMenu(true);
    // window.addEventListener('resize', this.renderMenu , { passive: true });
  }



  getCategories = (locale = "en-GB", desc = 'All') =>
    categoryApi.findByDesc(locale, desc)
    .then((response) => {
      return response.text();
    })
    .then((responseText) => {
      return JSON.parse(responseText);
    })
    .catch(()=>{
      console.log('getCategories failed!');
  });

  render() {
    const { category } = this.state;
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          <li><a className="active" href="#">Beans</a></li>
          <li><a href="#">Bread</a></li>
          <li><a href="#">Eggs</a></li>
          <li><a href="#">Fruits</a></li>
          <li><a href="#">Salads</a></li>
          <li><a href="#">Fast Foods</a></li>
          <li><a href="#">Fish & Meats</a></li>
          <li><a href="#">Uncategorized</a></li>
        </ul>
      </div>
    );
  }
}

export default CategorySidebar;
