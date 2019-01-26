import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

const sortAliases = {
  1:"name (desc)",
  2:"price (desc)",
}

class PageSort extends Component {

  changePageSort = (event) => {
    let urlParams = (qs.parse(this.props.history.location.search));
    let mergedParams = Object.assign(urlParams, {
                                                  sort: event.target.id,
                                                  page: 0,
                                                });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }


  render() {
    return (
      <div>
      </div>
    )
  }

}

export default withRouter(PageSort);
