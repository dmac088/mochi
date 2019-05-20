import React from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../../data/categories/api';
import { changeCategory, createRouteProps } from '../../../services/helpers/routeHelper';
import {
  PRIMARY_CATEGORY_FACET_NAME,
  SECONDARY_CATEGORY_FACET_NAME,
  BRAND_FACET_NAME,
  PRICE_FACET_NAME,
  TAG_FACET_NAME,
} from '../../../services/helpers/facetHelper';
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
          {renderSection("Categories", selectedFacets.filter(o => o.facetingName === PRIMARY_CATEGORY_FACET_NAME || o.facetingName === SECONDARY_CATEGORY_FACET_NAME), routeProps, props)}
          {renderSection("Brands", selectedFacets.filter(o => o.facetingName === BRAND_FACET_NAME), routeProps, props)}
          {renderSection("Price Ranges", selectedFacets.filter(o => o.facetingName === PRICE_FACET_NAME), routeProps, props)}
          {renderSection("Tags", selectedFacets.filter(o => o.facetingName === TAG_FACET_NAME), routeProps, props)}
        </ul>
      </div>
    );
  });
