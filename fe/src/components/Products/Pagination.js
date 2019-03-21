import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class Pagination extends Component {


  componentDidUpdate() {
    console.log("componentDidUpdate");
  }

  changePage = (e) => {
    e.preventDefault();
    const { callback } = this.props;
    const { pathname, search } = this.props.location;
    const urlParams = (qs.parse(this.props.history.location.search));
    const mergedParams = Object.assign(urlParams, { page: e.currentTarget.id });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": pathname,
      "search": searchString,
    });
    callback();
  }

  renderPaginator = (pages, current) => {
    return Array.apply(null, {length: pages-1}).map(Number.call,page => {
      console.log(page)
      return (
        <li>
          <a  key={page}
              id={page}
              href="#"
              className={(Number(page) ===  Number(current)) ?  "active" : ""}
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
                  <li><a href="#"><i className="fa fa-angle-right"></i></a></li>
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
