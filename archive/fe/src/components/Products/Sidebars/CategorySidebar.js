import React from "react";
import { withRouter } from "react-router-dom";
import * as categoryApi from "../../../data/categories/api";
import {
  changeCategory,
  createRouteProps
} from "../../../services/helpers/routeHelper";
import _ from "lodash";

const renderChevron = (facet, routeProps, props) => {
  return (
    <a
      className="isParent"
      id={facet.facetDisplayValue}
      onClick={e => {
        e.preventDefault();
        changeCategory(e, routeProps);
      }}
    ></a>
  );
};

const renderFacets = (facets, selectedFacets, routeProps, props) => {
  const minLevel = facets.reduce(
    (acc, x) => {
      acc.min = Math.min(acc.min, x.facetLevel);
      return acc;
    },
    { min: Infinity }
  ).min;
  return facets.map(facet => {
    const margin = (facet.facetLevel - minLevel) * 15;
    return (
        <li key={facet.facetId}>
          <a
            className={
              props.isActive(facet, selectedFacets, facets) ? "active" : ""
            }
            onClick={e => {
              e.preventDefault();
              props.updateFacets(e, routeProps);
            }}
            id={facet.token}
            href="#"
            style={{ marginLeft: margin }}
          >
            {facet.facetDisplayValue} ({facet.facetProductCount})
          </a>
          {facet.facetChildCount > 0
            ? renderChevron(facet, routeProps, props)
            : null}
        </li>
    );
  });
};

const sortFacets = (facets) => {
  const newFacets = [...facets];
  return newFacets.sort((a, b) => {
    if(a.token < b.token) { return -1; }
    if(a.token > b.token) { return 1; }
    return 0;
  });
}

export const CategorySidebar = withRouter(
  ({ location, match, history, ...props }) => {
    const { facets, selectedFacets } = props;
    const routeProps = createRouteProps(history, match, location);
    if (!facets) {
      return null;
    }
    if (!(facets.length > 0)) {
      return null;
    }
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          {renderFacets(sortFacets(facets), sortFacets(selectedFacets), routeProps, props)}
        </ul>
      </div>
    );
  }
);
