import React from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../../data/categories/api';
import { changeCategory, createRouteProps } from '../../../services/helpers/RouteHelper';
import _ from 'lodash';

  const renderCategories = (category, props) => {
    const { getMaxPrice } = props;
    return category.children.map(child => {
      return(
        <li key={child.categoryId} >
          <a onClick={(e) => changeCategory(e, props)} id={child.categoryDesc} href="#">
            {child.categoryDesc} ({child.productCount})
          </a>
        </li>
      );
    });
  }

  const renderFacets = (facets, selectedFacets, routeProps, props) => {
    return facets.map(facet => {
      const margin = (facet.level - 1) * 15;
      return(
        <li key={facet.id}>
          <a className={(props.isActive(facet, selectedFacets, facets)) ? "active" : ""}
             onClick={(e) => {
                                e.preventDefault();
                                props.applyFacet(e, routeProps);
                             }}
             id={facet.token} href="#"
             style={{"marginLeft": margin}}>
            {facet.desc} ({facet.count})
          </a>
        </li>
      );
    });
  }

  export const CategorySidebar = withRouter(({location, match, history, ...props}) => {
    const { category, facets, selectedFacets } = props;
    const routeProps = createRouteProps(history, match, location);
    const isSearch = (routeProps.match.params[0].toLowerCase() === "search");
    const isCategory = (routeProps.match.params[0].toLowerCase() === "category");
    if(isCategory && !category) {return null}
    if(isCategory && !(category.children)) {return null}
    if(isCategory && category.children.length === 0) { return null }
    if(isSearch && !facets) { return null }
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          {(isCategory)
            ? renderCategories(category, routeProps)
            : renderFacets(facets, selectedFacets, routeProps, props)}
        </ul>
      </div>
    );
  });
