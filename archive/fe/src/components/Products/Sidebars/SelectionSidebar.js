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
        <li key={facet.facetId}>
          <a className="active"
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
    if(!props.selectedFacets) { return null; }
    const { productCategories, prices, brands, tags } = props.selectedFacets;
    const routeProps = createRouteProps(history, match, location);

    if(!(  productCategories.length  > 0
        || prices.length      > 0
        || brands.length      > 0
        || tags.length        > 0)) { return null; }

    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">SELECTIONS</h3>
        <ul className="selected-categories">
          {renderSection("Categories", productCategories, routeProps, props)}
          {renderSection("Brands", brands, routeProps, props)}
          {renderSection("Price Ranges", prices, routeProps, props)}
          {renderSection("Tags", tags, routeProps, props)}
        </ul>
      </div>
    );
  });
