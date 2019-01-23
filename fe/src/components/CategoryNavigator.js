import React, { Component } from 'react';
import { ListGroup, ListGroupItem, Badge }  from 'react-bootstrap';
import { Link }  from 'react-router-dom';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class CategoryNavigator extends Component {

  constructor(props) {
    super(props);
  }

  changeCategory = (event) => {
    //get the query parameters
    let params = (qs.parse(this.props.location.search));

    //this will cause a refresh of App, since that is where the route is defined
    this.props.history.push({
        "pathname": '/Search',
        "search":   '?lang='
                    + ((params.lang === undefined) ? "ENG" : params.lang)
                    + '&category=' + ((event.target.id === undefined) ? "ALL" : event.target.id)
                    + '&term='
                    + ((params.term === undefined) ? "" : params.term)
                    + '&page='
                    + ((params.page === undefined) ? "0" : params.page)
                    + '&size='
                    + ((params.size === undefined) ? "10" : params.size)
                    + '&sort='
                    + ((params.sort === undefined) ? "2" : params.sort)
      });
      this.props.refreshData();
  }

  renderCategoryListItems = (categoryList, changeCategory) => {
    return categoryList = categoryList.map(category => {
      return(
          <React.Fragment>
            <ListGroupItem key={category.categoryId} id={category.categoryCode} onClick={this.changeCategory}>
              {category.categoryDesc}<Badge key={category.categoryId} id={category.categoryCode} pill="true">{category.productCount}</Badge>
            </ListGroupItem>
          </React.Fragment>
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
