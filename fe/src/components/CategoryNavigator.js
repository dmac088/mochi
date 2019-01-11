import React from 'react';
import { ListGroup, ListGroupItem, Badge }  from 'react-bootstrap';

const CategoryNavigator = (props) => {
  return (
    <ul>
      {renderCategoryListItems(props.categoryList, props.changeCategory)}
    </ul>
  );
}

const renderCategoryListItems = (categoryList, changeCategory) => {
  return categoryList = categoryList.map(category => {
      return(
        <React.Fragment>
          <li key={category.categoryId} id={category.categoryId} onClick={changeCategory}  tag="a" href="#">{category.categoryDesc} <Badge pill>{category.productCount}</Badge></li>
        </React.Fragment>
      )
    });
}

export default CategoryNavigator;
