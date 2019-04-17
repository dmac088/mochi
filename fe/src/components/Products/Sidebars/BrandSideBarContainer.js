import React from 'react';
import { withRouter } from "react-router-dom";
import { changeBrand, createRouteProps } from '../../../services/helpers/RouteHelper';

  const renderBrandsForCategory = (category, currentBrand, routeProps) => {
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

  const renderBrandFacets = (brandFacets, currentBrand, routeProps) => {
    if(!brandFacets) {return}
    return brandFacets.map(brandFacet => {
      const isActive = (currentBrand === brandFacet.value)
      return(
        <li key={brandFacet.value}>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, routeProps)} id={brandFacet.value} href="#">
            {brandFacet.value} <span className="badge badge-pill badge-secondary">{brandFacet.count}</span>
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

  export const BrandSideBarContainer = withRouter(({history, match, location, ...props}) => {
    const { category, brandFacets } = props;
    const routeProps = createRouteProps(history, match, location);
    const currentBrand = routeProps.match.params.brand;
    const isActive = (!currentBrand);
    const isSearch = (match.params[0] === "search");
    const isCategory = (match.params[0] === "category");
    if(isCategory && !category) { return null }

    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {(isCategory)
              ? renderAll(category, isActive, routeProps)
              : null}
            {(isCategory)
              ? renderBrandsForCategory(category, currentBrand, routeProps)
              : renderBrandFacets(brandFacets, currentBrand, routeProps)}
          </ul>
        </div>
      );
  });
