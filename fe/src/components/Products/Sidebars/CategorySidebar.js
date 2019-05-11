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

  const renderChevron = (facet, routeProps, props) => {
    return (
      <a
        className="isParent"
        id={facet.desc}
        onClick={(e) => {
                        e.preventDefault();
                        changeCategory(e, routeProps);
                      }}>
      </a>
    );
  }

  const renderFacets = (facets, selectedFacets, routeProps, props) => {
    const minLevel = facets.reduce((acc, x) => {
                    acc.min = Math.min(acc.min, x.level)
                    return acc }, { min : Infinity}).min;
    return facets.map(facet => {

      const margin = (facet.level - minLevel) * 15;
      return(
        <React.Fragment>
          <li key={facet.id}>
            <a className={(props.isActive(facet, selectedFacets, facets)) ? "active" : ""}
               onClick={(e) => {
                                  e.preventDefault();
                                  props.applyFacet(e, routeProps);
                               }}
               id={facet.token} href="#"
               style={{"marginLeft": margin}}>
              {facet.desc} ({facet.productCount})
            </a>
            {(facet.parent) ? renderChevron(facet, routeProps, props) : null}
          </li>
        </React.Fragment>
      );
    });
  }

  export const CategorySidebar = withRouter(({location, match, history, ...props}) => {
    const { facets, selectedFacets } = props;
    const routeProps = createRouteProps(history, match, location);
    if(!(facets.length > 0)) { return null }
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          {renderFacets(facets, selectedFacets, routeProps, props)}
        </ul>
      </div>
    );
  });
