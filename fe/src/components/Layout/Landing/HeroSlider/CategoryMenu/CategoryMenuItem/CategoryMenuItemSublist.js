import React from 'react';

function CategoryMenuItemSubList(props) {

    let container = null;

    const setContainer = (c) => {
        container = c;
    }

    const { isMobile, fullList, renderCategoryList, itemCounter, children } = props;
    
    return (
        <ul ref={setContainer}
            className="category-mega-menu">
             {renderCategoryList(isMobile, children, fullList, false, itemCounter)} 
        </ul>
    )
}

export default CategoryMenuItemSubList;