import React, { useState } from 'react';
import { Transition } from 'react-transition-group'
import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';
import CategoryMenuItem from './CategoryMenuItem/CategoryMenuItem';
import { mockCategory, mockCategoryList } from './mockData';

function CategoryMenu(props) {

    // const renderCategoryList = (locale, isMobile, displayCategoryList, categoryList, isRootList, itemCounter, routeProps) => {
    //     if (!displayCategoryList) { return; }
    //     return displayCategoryList.map(category => {
    //         if (isRootList) { itemCounter += 1 };
    //         const { showMore } = this.state
    //         const categoryId = category.facetId;

    //         return (
    //             <ReactTransitionGroup
    //                 key={categoryId}
    //                 component={CategoryMenuItem}
    //                 locale={locale}
    //                 isMobile={isMobile}
    //                 category={category}
    //                 isRootList={isRootList}
    //                 itemCounter={itemCounter}
    //                 routeProps={routeProps}
    //                 showMore={showMore}
    //                 displayCategoryList={displayCategoryList}
    //                 categoryList={categoryList}
    //                 renderCategoryList={this.renderCategoryList}>
    //             </ReactTransitionGroup>
    //         )
    //     });
    // }

    let container = null;

    const setScope = (c) => {
        container = c;
    }

    const slide = (container, direction, params = { duration: 500 }) => {
        const element = ReactDOM.findDOMNode(container);
        if (element === undefined) { return; }
        Velocity(element, direction, params);
    }

    return (
        <Transition in={props.in}
            timeout={2000}
            onEnter={() => { console.log('enter') }}
            onEntering={() => {
                console.log('entering');
                slide(container, 'slideDown', null);
            }}
            onEntered={() => { console.log(' entered') }}
            onExit={() => { console.log(' exit') }}
            onExiting={() => {
                console.log('exiting');
                slide(container, 'slideUp', null);
            }}
            onExited={() => { console.log(' exited') }}>
            <ul ref={setScope}>

                <CategoryMenuItem
                    category={mockCategory}
                    categoryList={mockCategoryList._embedded.categoryResources}
                />

            </ul>


        </Transition>
    );
}

export default CategoryMenu;