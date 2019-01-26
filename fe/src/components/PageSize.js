import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import qs from 'query-string';


class PageSize extends Component {

  changePageSize = (event) => {
    let urlParams = (qs.parse(this.props.history.location.search));
    let mergedParams = Object.assign(urlParams, { size: event.target.id });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }


  render() {
    return (
      <div></div>
    )
  }

};

export default withRouter(PageSize);
