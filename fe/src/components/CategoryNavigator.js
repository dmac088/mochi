import React from 'react';

const renderCategoryListItems = (categoryList, categoryClick) => {
  return categoryList = categoryList.map(category => {
      return(
        <button key={category.categoryId} id={category.categoryId} onClick={categoryClick}>{category.categoryDesc}</button>
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
