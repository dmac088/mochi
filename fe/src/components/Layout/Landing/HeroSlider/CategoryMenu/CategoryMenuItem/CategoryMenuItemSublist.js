import React from 'react';

function CategoryMenuItemSubList(props) {

    const { isMobile, fullList, renderCategoryList, itemCounter, children } = props;
    
    return (
        <React.Fragment>
             {renderCategoryList(isMobile, children, fullList, false, itemCounter)} 
        </React.Fragment>
    )
}

export default CategoryMenuItemSubList;