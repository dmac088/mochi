import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import qs from 'query-string';
import { createRouteProps } from '../../services/helpers/routeHelper';

  const changePage = (e, routeProps) => {
    e.preventDefault();
    const page = e.currentTarget.id;
    if(Number(page) > Number(e.currentTarget.getAttribute("pages"))-1) { return; }
    const { pathname, search } = routeProps.location;
    const urlParams = qs.parse(search);
    const searchString = qs.stringify(Object.assign(urlParams, { page: page }));

    routeProps.history.push({
      "pathname": pathname,
      "search": searchString,
    });
  }

  const renderPaginator = (pages, current, routeProps) => {
    return Array.apply(null, {length: pages}).map(Number.call,page => {
      return (
        <li key={page}>
          <a
              id={page}
              href="#"
              className={(Number(page) ===  Number(current)) ?  "active" : ""}
              pages={pages}
              onClick={(e) => changePage(e, routeProps)}>
            {page+1}
          </a>
        </li>
      )
    });
  }

  const renderNext = (pages, currentPage, routeProps) => {
    if(Number(currentPage) === (Number(pages)-1)) { return null; }
    return (
      <li>
        <a  onClick={(e) => changePage(e, routeProps)}
            id={Number(currentPage)+1}
            pages={pages}
            href="#">
            <i className="fa fa-angle-right"></i>
        </a>
      </li>
    );
  }

  export const Pagination = withRouter(({history, match, location, ...props}) => {
    const {totalPages, currentPage } = props;
    const routeProps = createRouteProps(history, match, location);
    if (totalPages <= 1 ) { return null }
		return (
      <div className="pagination-container">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="pagination-content text-center">
                <ul>
                  {renderPaginator(totalPages, currentPage, routeProps)}
                  {renderNext(totalPages, currentPage, routeProps)}
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  });
