import React, { Component } from 'react';
import * as categoryApi from '../../../data/categories/api';

class CategorySidebar extends Component {

  renderCategoryListItems = (category) => {
    const { changeCategory } = this.props;
    return category.children.map(child => {
      return(
        <li key={child.categoryId} >
          <a onClick={changeCategory} id={child.categoryDesc} href="#">
            {child.categoryDesc} <span className="badge badge-pill badge-secondary">{child.productCount}</span>
          </a>
        </li>
      )
    })
  }

  render() {
    const { category } = this.props;
    if(!category) {return null;}
    if(category.children.length === 0) {return null;}
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
