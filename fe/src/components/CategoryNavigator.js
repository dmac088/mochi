import React, { Component } from 'react';

import { withRouter } from "react-router-dom";
import qs from 'query-string';

class CategoryNavigator extends Component {

  changeCategory = (event) => {
    //get the query parameters
    let urlParams = (qs.parse(this.props.history.location.search));
    let mergedParams = Object.assign(urlParams, {
                                                  category: event.target.id,
                                                  page: 0
                                                });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }

  renderCategoryListItems = (categoryList, changeCategory) => {
    return categoryList = categoryList.map(category => {
      return(
            <div>
            </div>
      )
    });
  }

  render() {
    return (
      <div>
      </div>
    );
  }

}
export default withRouter(CategoryNavigator);
