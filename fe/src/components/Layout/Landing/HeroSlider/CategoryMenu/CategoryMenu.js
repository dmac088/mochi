import React, { useState } from 'react';
import { TransitionGroup, Transition } from 'react-transition-group'
import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';
import CategoryMenuItem from './CategoryMenuItem/CategoryMenuItem';
import { mockCategory, mockCategoryList } from './mockData';

function CategoryMenu(props) {

    const renderCategoryList = (//tells us if we are in mobile mode
                                isMobile, 
                                //display categories and sort order defined here, list of codes
                                displayList, 
                                //the actual list of objects to be displayed
                                dataList, 
                                //is it the root list (level = 0)
                                isRoot, 
                                //counting items in the list
                                itemCounter) => {
        if (!displayCategoryList) { return; }
        return displayCategoryList.map(category => {
            if (isRootList) { itemCounter += 1 };
            //const { showMore } = this.state;
            return (
                <TransitionGroup
                    key={category.data.categoryCode}
                    component={CategoryMenuItem}
                    isMobile={isMobile}
                    category={category}
                    isRoot={isRoot}
                    itemCounter={itemCounter}
                    displayList={displayList}
                    dataList={dataList}
                    renderCategoryList={renderCategoryList}>
                </TransitionGroup>
            )
        });
    }

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