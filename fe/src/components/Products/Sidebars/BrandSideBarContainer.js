import React from 'react';
import { withRouter } from "react-router-dom";
import { changeBrand, createRouteProps } from '../../../services/helpers/RouteHelper';

  const renderBrandsForCategory = (category, selectedFacet, routeProps) => {
    return category.categoryBrands.map(brand => {
      const isActive = (selectedFacet === brand.brandDesc)
      return(
        <li key={brand.brandId}>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, routeProps)} id={brand.brandDesc} href="#">
            {brand.brandDesc} ({brand.productCount})
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

  const renderFacets = (facets, selectedFacets, routeProps, props) => {
    return facets.map(facet => {
      return(
        <li key={facet.id}>
          <a className={(props.isActive(facet, selectedFacets, facets)) ? "active" : ""} onClick={(e) => {
                                e.preventDefault();
                                props.applyFacet(e, routeProps);
                             }} id={facet.token} href="#">
            {facet.desc} ({facet.count})
          </a>
        </li>
      );
    });
  }

  export const BrandSidebarContainer = withRouter(({location, match, history, ...props}) => {
    const { category, facets, isActive, selectedFacets, brand } = props;
    const routeProps = createRouteProps(history, match, location);
    const isSearch = (match.params[0] === "search");
    const isCategory = (match.params[0] === "category");
    if(isCategory && !category) { return null }
    if(isSearch && !facets) { return null }
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {(isCategory)
              ? renderAll(category, isActive, routeProps)
              : null}
            {(isCategory)
              ? renderBrandsForCategory(category, selectedFacets, routeProps)
              : renderFacets(facets, selectedFacets, routeProps, props)}
          </ul>
        </div>
      );
  });
