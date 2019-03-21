import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class Pagination extends Component {

  changePage = (e) => {
    e.preventDefault();
    const page = e.currentTarget.id;
    if(Number(page) > Number(e.currentTarget.getAttribute("pages"))-1) { return; }
    const { pathname, search } = this.props.location;
    const urlParams = qs.parse(search);
    const searchString = qs.stringify(Object.assign(urlParams, { page: page }));

    this.props.history.push({
      "pathname": pathname,
      "search": searchString,
    });
  }

  renderPaginator = (pages, current) => {
    return Array.apply(null, {length: pages}).map(Number.call,page => {
      return (
        <li key={page}>
          <a
              id={page}
              href="#"
              className={(Number(page) ===  Number(current)) ?  "active" : ""}
              pages={pages}
              onClick={this.changePage}>
            {page+1}
          </a>
        </li>
      )
    });
  }

  render() {
    const { totalPages, currentPage } = this.props;
		return (
      <div className="pagination-container">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="pagination-content text-center">
                <ul>
                  {this.renderPaginator(totalPages, currentPage)}
                  <li><a onClick={this.changePage} id={Number(currentPage)+1} pages={totalPages} href="#"><i className="fa fa-angle-right"></i></a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default withRouter(Pagination);
