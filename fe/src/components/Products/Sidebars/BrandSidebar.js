import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class BrandSidebar extends Component {

  render() {
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            <li><a className="active" href="shop-left-sidebar.html">Gold</a></li>
            <li><a href="shop-left-sidebar.html">Green</a></li>
            <li><a href="shop-left-sidebar.html">White</a></li>
          </ul>
        </div>
      );
  }
}

export default BrandSidebar;
