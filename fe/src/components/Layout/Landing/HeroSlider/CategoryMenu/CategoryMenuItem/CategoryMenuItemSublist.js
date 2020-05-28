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

    const { locale, itemCounter, children, isMobile, routeProps, categoryList, renderCategoryList } = props;
    return (
        <ul ref={setContainer}
            className="category-mega-menu">
            {/* {renderCategoryList(locale, isMobile, children, categoryList, false, itemCounter, routeProps)} */}
        </ul>
    )
}

export default CategoryMenuItemSubList;