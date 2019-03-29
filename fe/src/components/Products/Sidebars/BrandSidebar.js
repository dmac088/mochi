import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class BrandSidebar extends Component {

  renderBrandListItems = (brands) => {
    return brands.map(brand => {
      return(
        <li key={brand.brandId} >
          <a id={brand.brandDesc} href="#">
            {brand.brandDesc}
          </a>
        </li>
      )
    })
  }

  render() {
    const { brands } = this.props;
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {this.renderBrandListItems(brands)}
          </ul>
        </div>
      );
  }
}

export default BrandSidebar;
