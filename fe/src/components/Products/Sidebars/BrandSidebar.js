import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class BrandSidebar extends Component {

  renderBrandListItems = (category, currentBrand, changeBrand, getMaxPrice) => {
    return category.categoryBrands.map(brand => {
      const isActive = (currentBrand === brand.brandDesc)
      return(
        <li key={brand.brandId}>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, ()  => getMaxPrice(category, brand))} id={brand.brandDesc} href="#">
            {brand.brandDesc} <span className="badge badge-pill badge-secondary">{brand.productCount}</span>
          </a>
        </li>
      )
    })
  }

  renderAll = (category, isActive, changeBrand, getMaxPrice) => {
      if(category.categoryBrands.length <= 1) {return}
      return (
        <li>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e)} id={"All"} href="#">
            All
          </a>
        </li>
      )
  }


  render() {
    const { category, changeBrand, getMaxPrice } = this.props;
    const currentBrand = this.props.match.params.brand;
    const isActive = (!currentBrand);
    if (!category.categoryBrands) { return null; }
    if(category.categoryBrands.length <= 1) {return null; }
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {this.renderAll(category, isActive, changeBrand, getMaxPrice)}
            {this.renderBrandListItems(category,  currentBrand, changeBrand, getMaxPrice)}
          </ul>
        </div>
      );
  }
}

export default withRouter(BrandSidebar);
