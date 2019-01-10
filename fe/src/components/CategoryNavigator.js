import React from 'react';
import { ListGroup, ListGroupItem, Badge } from 'reactstrap';

const renderCategoryListItems = (categoryList, categoryClick) => {
  return categoryList = categoryList.map(category => {
      return(
        <ListGroupItem key={category.categoryId} id={category.categoryId} onClick={categoryClick}  tag="a" href="#">{category.categoryDesc} <Badge pill>{category.productCount}</Badge></ListGroupItem>
      )
    });
}

const CategoryNavigator = (props) => {
  return (
    <ListGroup>
      {renderCategoryListItems(props.categoryList, props.categoryClick)}
    </ListGroup>
  );
}


export default CategoryNavigator;
