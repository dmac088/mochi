import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class CategorySidebar extends Component {

  render() {
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          <li><a className="active" href="shop-left-sidebar.html">Beans</a></li>
          <li><a href="shop-left-sidebar.html">Bread</a></li>
          <li><a href="shop-left-sidebar.html">Eggs</a></li>
          <li><a href="shop-left-sidebar.html">Fruits</a></li>
          <li><a href="shop-left-sidebar.html">Salads</a></li>
          <li><a href="shop-left-sidebar.html">Fast Foods</a></li>
          <li><a href="shop-left-sidebar.html">Fish & Meats</a></li>
          <li><a href="shop-left-sidebar.html">Uncategorized</a></li>
        </ul>
      </div>
    );
  }
}

export default CategorySidebar;
