import React, {Component} from 'react';
import * as productService from '../services/product';

const renderCategoryListItems = (categoryList, categoryClick) => {
  return categoryList = categoryList.map(category => {
      return(
        <button id={category.categoryId} onClick={categoryClick}>{category.categoryDesc}</button>
      )
    });
}

const CategoryNavigator = (props) => {
  return (
    <ul>
      {renderCategoryListItems(props.categoryList, props.categoryClick)}
    </ul>
  );
}


export default CategoryNavigator;
