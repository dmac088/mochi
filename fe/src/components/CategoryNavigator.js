import React, { Component } from 'react';
import { ListGroup, ListGroupItem, Badge }  from 'react-bootstrap';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class CategoryNavigator extends Component {

  changeCategory = (event) => {
    //get the query parameters
    const query =  { category: event.target.id };
    const searchString = qs.stringify(query);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }

  renderCategoryListItems = (categoryList, changeCategory) => {
    return categoryList = categoryList.map(category => {
      return(
            <ListGroupItem key={category.categoryId} id={category.categoryCode} onClick={this.changeCategory}>
              {category.categoryDesc}<Badge key={category.categoryId} id={category.categoryCode} pill="true">{category.productCount}</Badge>
            </ListGroupItem>
      )
    });
  }

  render() {
    return (
      <ListGroup>
        {this.renderCategoryListItems(this.props.categoryList, this.props.changeCategory)}
      </ListGroup>
    );
  }

}
export default withRouter(CategoryNavigator);
