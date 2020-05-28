import React from 'react';

function CategoryMenuItemSubList(props) {


    const { isMobile, fullList, renderCategoryList, itemCounter, children } = props;
    
    return (
        <ul className="category-mega-menu">
             {renderCategoryList(isMobile, children, fullList, false, itemCounter)} 
        </ul>
    )
}

export default CategoryMenuItemSubList;