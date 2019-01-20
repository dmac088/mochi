import React from 'react';
import { ListGroup, ListGroupItem, Badge }  from 'react-bootstrap';

const CategoryNavigator = (props) => {
  return (
    <ListGroup>
      {renderCategoryListItems(props.categoryList, props.changeCategory)}
    </ListGroup>
  );
}

const renderCategoryListItems = (categoryList, changeCategory) => {
  return categoryList = categoryList.map(category => {
      return(
          <ListGroupItem key={category.categoryId} id={category.categoryCode} onClick={changeCategory}>{category.categoryDesc}
            <Badge key={category.categoryId} id={category.categoryCode} pill="true">{category.productCount}</Badge>
          </ListGroupItem>
      )
    });
}

export default CategoryNavigator;
