import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class BrandSidebar extends Component {

  render() {
    return (
        <div class="sidebar mb-35">
          <h3 class="sidebar-title">Filter By Brand</h3>
          <ul class="product-categories">
            <li><a class="active" href="shop-left-sidebar.html">Gold</a></li>
            <li><a href="shop-left-sidebar.html">Green</a></li>
            <li><a href="shop-left-sidebar.html">White</a></li>
          </ul>
        </div>
      );
  }
}

export default BrandSidebar;
