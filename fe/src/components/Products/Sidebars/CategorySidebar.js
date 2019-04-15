import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../../data/categories/api';
import { changeCategory } from '../../../services/helpers/RouteHelper';

class CategorySidebar extends Component {

  renderCategoryListItems = (category) => {
    const { getMaxPrice } = this.props;
    return category.children.map(child => {
      return(
        <li key={child.categoryId} >
          <a onClick={(e) => changeCategory(e, this.props)} id={child.categoryDesc} href="#">
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

export default withRouter(CategorySidebar);
