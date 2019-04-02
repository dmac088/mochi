import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class BrandSidebar extends Component {

  renderBrandListItems = (brands, currentBrand, changeBrand) => {
    return brands.map(brand => {
      const isActive = (currentBrand === brand.brandDesc)
      return(
        <li key={brand.brandId}>
          <a className={(isActive) ? "active" : ""} onClick={changeBrand} id={brand.brandDesc} href="#">
            {brand.brandDesc} <span className="badge badge-pill badge-secondary">{brand.productCount}</span>
          </a>
        </li>
      )
    })
  }

  renderAll = (brands, isActive, changeBrand) => {
      if(brands.length <= 1) {return}
      return (
        <li>
          <a className={(isActive) ? "active" : ""} onClick={changeBrand} id={"All"} href="#">
            All
          </a>
        </li>
      )
  }


  render() {
    const { brands, changeBrand } = this.props;
    const currentBrand = this.props.match.params.brand;
    const isActive = (!currentBrand);
    if (!brands) { return null; }
    //if(brands.length <= 1) {return null; }
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {this.renderAll(brands, isActive, changeBrand)}
            {this.renderBrandListItems(brands,  currentBrand, changeBrand)}
          </ul>
        </div>
      );
  }
}

export default BrandSidebar;
