import React, { useState } from 'react';
import { TransitionGroup, Transition } from 'react-transition-group'
import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';
import CategoryMenuItem from './CategoryMenuItem/CategoryMenuItem';
import { mockCategory, mockCategoryList, mockDisplayList } from './mockData';

function CategoryMenu(props) {

    const renderCategoryList = (//tells us if we are in mobile mode
                                isMobile, 
                                //display categories and sort order defined here, list of codes
                                renderList, 
                                //the actual list of objects to be displayed
                                fullList, 
                                //is it the root list (level = 0)
                                isRoot, 
                                //counting items in the list
                                itemCounter) => {

        if (!renderList) { return; }

        
        return renderList.map(category => {
            
            if (isRoot) { itemCounter += 1 };

            return (
                <TransitionGroup
                    key={category.data.categoryCode}
                    component={CategoryMenuItem}
                    isMobile={isMobile}
                    category={category}
                    isRoot={isRoot}
                    itemCounter={itemCounter}
                    renderList={renderList}
                    fullList={fullList}
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
                    isMobile={false}
                    isRoot={true}
                    renderList={null}
                    fullList={mockCategoryList}
                    category={mockCategory}
                    renderCategoryList={renderCategoryList}
                    itemCounter={0}
                />

            </ul>

        </Transition>
    );
}

export default CategoryMenu;