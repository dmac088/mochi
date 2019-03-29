import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class BrandSidebar extends Component {

  renderBrandListItems = (brands, changeBrand) => {
    const currentBrand = this.props.match.params.brand;
    console.log(currentBrand);

    return brands.map(brand => {
      const isActive = (currentBrand === brand.brandDesc)
      return(
        <li key={brand.brandId} >
          <a className={(isActive) ? "active" : ""} onClick={changeBrand} id={brand.brandDesc} href="#">
            {brand.brandDesc}
          </a>
        </li>
      )
    })
  }

  render() {
    const { brands, changeBrand } = this.props;
    if (!brands) { return null; }
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
