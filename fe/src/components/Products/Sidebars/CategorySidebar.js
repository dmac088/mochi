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

  renderCategoryListItems = (category) => {
    if(!category) { return null; }
    return category.children.map(child => {
      return(
            <li key={child.categoryId} >
              <a id={child.categoryCode} href="#">
                {child.categoryDesc}
              </a>
            </li>
      )
    })
  }

  render() {
    const { category } = this.state;

    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          {this.renderCategoryListItems(category)}
        </ul>
      </div>
    );
  }
}

export default CategorySidebar;
