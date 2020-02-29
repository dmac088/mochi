import React from 'react';
import { withRouter } from "react-router-dom";
import { createRouteProps } from '../../../services/helpers/routeHelper';

  const renderFacets = (facets, selectedFacets, routeProps, props) => {
    if(facets.length === 0) { return null }
    return facets.map(facet => {
      return(
        <li key={facet.facetId}>
          <a  className={(props.isActive(facet, selectedFacets, facets)) ? "active" : ""}
              onClick={(e) => {
                                e.preventDefault();
                                props.updateFacets(e, routeProps);
                              }}
              id={facet.token}
              href="#">
            {facet.facetDisplayValue} ({facet.facetProductCount})
          </a>
        </li>
      );
    });
  }

  export const BrandSidebarContainer = withRouter(({location, match, history, ...props}) => {
    const { facets, selectedFacets } = props;
    if(!facets) { return null; }
    if(!(facets.length > 0)) { return null; }
    const routeProps = createRouteProps(history, match, location);
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
             {renderFacets(facets, selectedFacets, routeProps, props)}
          </ul>
        </div>
      );
  });
