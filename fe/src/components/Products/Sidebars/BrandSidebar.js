import React from 'react';
import { withRouter } from "react-router-dom";
import { changeBrand, createRouteProps   } from '../../../services/helpers/RouteHelper';

  const renderBrandListItems = (category, currentBrand, routeProps) => {
    return category.categoryBrands.map(brand => {
      const isActive = (currentBrand === brand.brandDesc)
      return(
        <li key={brand.brandId}>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, routeProps)} id={brand.brandDesc} href="#">
            {brand.brandDesc} <span className="badge badge-pill badge-secondary">{brand.productCount}</span>
          </a>
        </li>
      )
    })
  }

  const renderAll = (category, isActive, routeProps) => {
      if(category.categoryBrands.length <= 1) {return}
      return (
        <li>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, routeProps)} id={"All"} href="#">
            All
          </a>
        </li>
      )
  }

  export const BrandSidebar = withRouter(({history, match, location, ...props}) => {
    const { category } = props;
    const routeProps = createRouteProps(history, match, location);
    const currentBrand = routeProps.match.params.brand;
    const isActive = (!currentBrand);
    if (!category.categoryBrands) { return null; }
    if(category.categoryBrands.length <= 1) {return null; }
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {renderAll(category, isActive, routeProps)}
            {renderBrandListItems(category, currentBrand, routeProps)}
          </ul>
        </div>
      );
  });
