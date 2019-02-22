import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class Paginator extends Component {


  render() {
    return (
        <div>
        </div>
    );
  }

  changePage = (event) => {
    let urlParams = (qs.parse(this.props.history.location.search));
    let mergedParams = Object.assign(urlParams, { page: event.target.id });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }

  renderPaginator = (pages) => {
    return Array.apply(null, {length: pages}).map(Number.call,page => {
      return (
        <li key={page} className="page-link" id={page}  onClick={this.changePage}>{page+1}</li>
      )
    });
  }

}

export default withRouter(Paginator);
