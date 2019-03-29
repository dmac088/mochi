import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class BrandSidebar extends Component {

  renderBrandListItems = (brands, changeBrand) => {

    return brands.map(brand => {
      return(
        <li key={brand.brandId} >
          <a onClick={changeBrand} id={brand.brandDesc} href="#">
            {brand.brandDesc}
          </a>
        </li>
      )
    })
  }

  render() {
    const { brands, changeBrand } = this.props;
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {this.renderBrandListItems(brands, changeBrand)}
          </ul>
        </div>
      );
  }
}

export default BrandSidebar;
