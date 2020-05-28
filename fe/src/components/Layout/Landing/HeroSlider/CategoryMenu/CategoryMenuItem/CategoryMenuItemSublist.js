import React from 'react';

function CategoryMenuItemSubList(props) {

    // componentWillEnter(callback) {
    //     if (!this.container) { return }
    //     slide(this.container, 'slideDown', { duration: 500, "display": "" }, callback);
    // }

    // componentWillLeave(callback) {
    //     if (!this.container) { return }
    //     slide(this.container, 'slideUp', null, callback);
    // }
    
    let container = null;

    const setContainer = (c) => {
        container = c;
    }

    const { isMobile, displayList, dataList, renderCategoryList, itemCounter, children } = props;
    
    return (
        <ul ref={setContainer}
            className="category-mega-menu">
             {renderCategoryList(isMobile, children, dataList, false, itemCounter)} 
        </ul>
    )
}

export default CategoryMenuItemSubList;