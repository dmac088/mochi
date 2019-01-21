import React, { Component } from 'react';
import { ListGroup, ListGroupItem, Badge }  from 'react-bootstrap';
import { Link }  from 'react-router-dom';
import { withRouter } from "react-router-dom";

class CategoryNavigator extends Component {

  constructor(props) {
    super(props);
  }

  myFunction = (event) => {
      this.props.history.push(
        pathname: '/category',
        search: '?name=' + event.target.id 
      );
  }

  renderCategoryListItems = (categoryList, changeCategory) => {
    return categoryList = categoryList.map(category => {
      return(
          <ListGroupItem key={category.categoryId} id={category.categoryCode} onClick={changeCategory}>
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
