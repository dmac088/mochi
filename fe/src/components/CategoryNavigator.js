import React, {Component} from 'react';

const renderCategoryListItems = (categoryList) => {
  return categoryList = categoryList.map(category => {
      return(
        <li key={category.categoryId}>{category.categoryDesc}</li>
      )
    });
}


const CategoryNavigator = (props) => {
  return (
    <ul>
      {renderCategoryListItems(props.categoryList)}
    </ul>
  );
}


export default CategoryNavigator;
