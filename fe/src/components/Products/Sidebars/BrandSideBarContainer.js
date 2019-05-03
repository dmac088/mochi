import React from 'react';
import { withRouter } from "react-router-dom";
import { createRouteProps } from '../../../services/helpers/RouteHelper';

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
    const { category, facets, isActive, selectedFacets } = props;
    const routeProps = createRouteProps(history, match, location);
    const isSearch = (match.params[0] === "search");
    const isCategory = (match.params[0] === "category");
    //if(isCategory && !category) { return null }
    if(isSearch && !facets) { return null }

    console.log("we made it!");
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
             {renderFacets(facets, selectedFacets, routeProps, props)}
          </ul>
        </div>
      );
  });
