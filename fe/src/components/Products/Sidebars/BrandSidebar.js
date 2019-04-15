import React from 'react';
import { withRouter } from "react-router-dom";
import { changeBrand } from '../../../services/helpers/RouteHelper';

  const renderBrandListItems = (category, currentBrand, props) => {
    const { location, match, history } = props;
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

  const renderAll = (category, isActive, props) => {
      if(category.categoryBrands.length <= 1) {return}
      const { location, match, history } = props;
      return (
        <li>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, location, match, history)} id={"All"} href="#">
            All
          </a>
        </li>
      )
  }

  export const BrandSidebar = withRouter(({...props}) => {
    const { category } = props;
    const currentBrand = props.match.params.brand;
    const isActive = (!currentBrand);
    if (!category.categoryBrands) { return null; }
    if(category.categoryBrands.length <= 1) {return null; }
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {renderAll(category, isActive, props)}
            {renderBrandListItems(category, currentBrand, props)}
          </ul>
        </div>
      );
  });
