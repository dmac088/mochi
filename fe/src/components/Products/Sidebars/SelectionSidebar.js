import React from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../../data/categories/api';
import { changeCategory, createRouteProps } from '../../../services/helpers/RouteHelper';
import _ from 'lodash';

  const renderFacets = (selectedFacets, routeProps, props) => {
    return selectedFacets.map(facet => {
      return (
        <li key={facet.id}>
          <a className="active"
             onClick={(e) => {
                                e.preventDefault();
                                props.applyFacet(e, routeProps);
                             }}
             id={facet.token}
             href="#">
            {facet.desc} ({facet.productCount})
          </a>
        </li>
      );
    });
  }

  const renderSection = (title, facets, routeProps, props) => {
    if(facets.length === 0) { return null }
    return (
      <React.Fragment>
        <p>{title}</p>
        {renderFacets(facets, routeProps, props)}
        <br/>
      </React.Fragment>
    );
  }

  export const SelectionSidebar = withRouter(({location, match, history, ...props}) => {
    const { selectedFacets } = props;
    const routeProps = createRouteProps(history, match, location);
    if(!selectedFacets.length > 0 ) { return null }
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">SELECTIONS</h3>
        <ul className="selected-categories">
          {renderSection("Categories", selectedFacets.filter(o => o.facetingName === "CategoryFR"), routeProps, props)}
          {renderSection("Brands", selectedFacets.filter(o => o.facetingName === "BrandFR"), routeProps, props)}
          {renderSection("Price Ranges", selectedFacets.filter(o => o.facetingName === "PriceFR"), routeProps, props)}
        </ul>
      </div>
    );
  });
