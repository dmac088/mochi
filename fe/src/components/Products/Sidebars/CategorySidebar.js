import React from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../../data/categories/api';
import { changeCategory, createRouteProps } from '../../../services/helpers/RouteHelper';


  const renderCategories = (category, props) => {
    const { getMaxPrice } = props;
    return category.children.map(child => {
      return(
        <li key={child.categoryId} >
          <a onClick={(e) => changeCategory(e, props)} id={child.categoryDesc} href="#">
            {child.categoryDesc} <span className="badge badge-pill badge-secondary">{child.productCount}</span>
          </a>
        </li>
      );
    });
  }

  const renderCategoryFacets = (categoryFacets, props) => {
    return categoryFacets.map(categoryFacet => {
      return(
        <li key={categoryFacet.value} >
          <a onClick={(e) => changeCategory(e, props)} id={categoryFacet.value} href="#">
            {categoryFacet.value} <span className="badge badge-pill badge-secondary">{categoryFacet.count}</span>
          </a>
        </li>
      );
    });
  }

  export const CategorySidebar = withRouter(({history, match, location, ...props}) => {
    //console.log(props);
    const { category, categoryFacets } = props;
    const routeProps = createRouteProps(history, match, location);
    const isSearch = (match.params[0].toLowerCase() === "search");
    const isCategory = (match.params[0].toLowerCase() === "category");
    if(isCategory && !category) {return null}
    if(isCategory && !(category.children)) {return null}
    if(isCategory && category.children.length === 0) { return null }
    if(isSearch && !categoryFacets) { return null }

    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          {(isCategory)
            ? renderCategories(category, routeProps)
            : renderCategoryFacets(categoryFacets, routeProps)}
        </ul>
      </div>
    );
  });
