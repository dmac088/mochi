import React, { Component } from 'react';
import * as categoryApi from '../../../data/categories/api';

class CategorySidebar extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "category": null,
    };
  }

  filterCategories = (categoryList, categoryDesc) => {
    return categoryList.filter(function(value, index, arr){
      return value.categoryDesc === categoryDesc;
    });
  }

  renderCategoryListItems = (category) => {
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
    const { categoryList } = this.props;
    const { term } = this.props.match.params;
    const category = this.filterCategories(categoryList, term)[0];

    if(!category) {return null;}
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
