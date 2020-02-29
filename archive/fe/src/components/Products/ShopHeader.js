import React from 'react';
import { withRouter } from 'react-router-dom';
import qs from 'query-string';
import { routeSingleProduct, createRouteProps } from '../../services/helpers/routeHelper';

  const changeSort = (e, routeProps) => {
    //get the query parameters
    const urlParams = (qs.parse(routeProps.location.search));
    const mergedParams = Object.assign(urlParams, {
                                                    sort: e.target.value
                                                  });
    const searchString = qs.stringify(mergedParams);
    routeProps.history.push({
      "pathname": routeProps.location.pathname,
      "search": searchString,
    });
  }


  export const ShopHeader = withRouter(({location, match, history, ...props}) => {
    const { toggleGrid, toggleList, currentPage, totalPages, totalElements, numberOfElements, size, isGrid } = props;
    const routeProps = createRouteProps(history, match, location);
    return (
      <div className="shop-header mb-35">
        <div className="row">
          <div className="col-lg-4 col-md-4 col-sm-12 d-flex align-items-center">
            <div className="view-mode-icons mb-xs-10">
              <a onClick={toggleGrid} className={(isGrid) ? "active" : ""} href="#" data-target="grid"><i className="fa fa-th"></i></a>
              <a onClick={toggleList} className={(!isGrid) ? "active" : ""} href="#" data-target="list" ><i className="fa fa-list"></i></a>
            </div>
          </div>
          <div className="col-lg-8 col-md-8 col-sm-12 d-flex flex-column flex-sm-row justify-content-between align-items-left align-items-sm-center">
            <div className="sort-by-dropdown d-flex align-items-center mb-xs-10">
              <p className="mr-10">Sort By: </p>
              <select onChange={(e) => changeSort(e, routeProps)} name="sort-by" id="sort-by" className="nice-select">
                <option value="nameAsc">Name [A-Z]</option>
                <option value="nameDesc">Name [Z-A]</option>
                <option value="priceAsc">Price: Low to High</option>
                <option value="priceDesc">Price: High to Low</option>
              </select>
            </div>
            <p className="result-show-message">Showing {((currentPage)*size)+1}–{((currentPage)*size)+Number(numberOfElements)} of {totalElements} results</p>
          </div>
        </div>
      </div>
    );
  });
