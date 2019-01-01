import React, {Component} from 'react';

const renderCategoryListItems = (categoryList) => {
  return categoryList = categoryList.map(category => {
      return(
        <button id={category.categoryId} onClick={categoryClick}>{category.categoryDesc}</button>
      )
    });
}

const categoryClick = (event) => {
  console.log('category ' + event.target.id + ' clicked!');
}

const CategoryNavigator = (props) => {
  return (
    <ul>
      {renderCategoryListItems(props.categoryList)}
    </ul>
  );
}


export default CategoryNavigator;
