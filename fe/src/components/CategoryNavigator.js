import React, { Component } from 'react';
import { updateParams } from '../services/helpers/ScreenHelper';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class CategoryNavigator extends Component {

  changeCategory = (event) => {
    updateParams(this.props.history.location.search,{category: event.target.id,page: 0},this.props.history);
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
