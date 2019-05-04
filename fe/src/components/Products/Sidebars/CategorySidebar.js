import React from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../../data/categories/api';
import { changeCategory, createRouteProps } from '../../../services/helpers/RouteHelper';
import _ from 'lodash';

  // const renderCategories = (category, props) => {
  //   const { getMaxPrice } = props;
  //   return category.children.map(child => {
  //     return(
  //       <li key={child.categoryId} >
  //         <a onClick={
  //                     //(e) => changeCategory(e, props)
  //                     (e) => {
  //                                       e.preventDefault();
  //                                       props.applyFacet(e, routeProps);
  //                    }
  //             id={child.categoryDesc}
  //             href="#">
  //           {child.categoryDesc} ({child.productCount})
  //         </a>
  //       </li>
  //     );
  //   });
  // }

  const renderFacets = (facets, selectedFacets, routeProps, props) => {
    return facets.map(facet => {
      const margin = (facet.level) * 15;
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

    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          {renderFacets(facets, selectedFacets, routeProps, props)}
        </ul>
      </div>
    );
  });
