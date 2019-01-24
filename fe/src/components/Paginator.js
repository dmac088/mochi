import React, { Component } from 'react';
import { Pagination }  from 'react-bootstrap';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class Paginator extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
        <nav aria-label="Page navigation example">
          <Pagination>
            <Pagination.First />
              {this.renderPaginator(this.props.totalPages)}
            <Pagination.Last />
          </Pagination>
        </nav>
    );
  }

  changePage = (event) => {
    //get the query parameters
    const query =  { page: event.target.id };
    const searchString = qs.stringify(query);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }

  renderPaginator = (pages) => {
    return Array.apply(null, {length: pages}).map(Number.call,page => {
      return (
        <Pagination.Item key={page} className="page-link" id={page}  onClick={this.changePage}>{page+1}</Pagination.Item>
      )
    });
  }

}

export default withRouter(Paginator);
