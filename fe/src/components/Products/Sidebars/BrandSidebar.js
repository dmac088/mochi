import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { changeBrand } from '../../../services/helpers/RouteHelper';

class BrandSidebar extends Component {

  renderBrandListItems = (category, currentBrand) => {
    const { location, match, history } = this.props;
    return category.categoryBrands.map(brand => {
      const isActive = (currentBrand === brand.brandDesc)
      return(
        <li key={brand.brandId}>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, location, match, history)} id={brand.brandDesc} href="#">
            {brand.brandDesc} <span className="badge badge-pill badge-secondary">{brand.productCount}</span>
          </a>
        </li>
      )
    })
  }

  renderAll = (category, isActive) => {
      if(category.categoryBrands.length <= 1) {return}
      const { location, match, history } = this.props;
      return (
        <li>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, location, match, history)} id={"All"} href="#">
            All
          </a>
        </li>
      )
  }


  render() {
    const { category } = this.props;
    const currentBrand = this.props.match.params.brand;
    const isActive = (!currentBrand);
    if (!category.categoryBrands) { return null; }
    if(category.categoryBrands.length <= 1) {return null; }
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {this.renderAll(category, isActive)}
            {this.renderBrandListItems(category,  currentBrand)}
          </ul>
        </div>
      );
  }
}

export default withRouter(BrandSidebar);
