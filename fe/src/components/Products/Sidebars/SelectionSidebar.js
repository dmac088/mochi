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

  const renderCategories = (categoryFacets, routeProps, props) => {
    if(categoryFacets.length === 0) { return null }
    return (
      <React.Fragment>
        <p>Categories</p>
        {renderFacets(categoryFacets, routeProps, props)}
      </React.Fragment>
    );
  }

  const renderBrands = (brandFacets, routeProps, props) => {
    if(brandFacets.length === 0) { return null }
    return (
      <React.Fragment>
        <p>Brands</p>
        {renderFacets(brandFacets, routeProps, props)}
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
          {renderCategories(selectedFacets.filter(o => o.facetingName === "CategoryFR"), routeProps, props)}
          <br/>
          {renderBrands(selectedFacets.filter(o => o.facetingName === "BrandFR"), routeProps, props)}
        </ul>
      </div>
    );
  });
