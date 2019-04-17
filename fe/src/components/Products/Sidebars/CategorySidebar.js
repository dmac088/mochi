import React from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../../data/categories/api';
import { changeCategory } from '../../../services/helpers/RouteHelper';


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

  export const CategorySidebar = withRouter(({match, ...props}) => {
    //console.log(props);
    const { category, categoryFacets } = props;
    const isSearch = (match.params[0] === "search");
    const isCategory = (match.params[0] === "category");
    if(isCategory && !category && !(category.children)) {return null;}
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          {(isCategory)
            ? renderCategories(category, props)
            : renderCategoryFacets(categoryFacets,props)}
        </ul>
      </div>
    );
  });
