import React from 'react';
import { TransitionGroup, Transition } from 'react-transition-group'
import { slide } from "../../../Helpers/Animation/Slide";
import CategoryMenuItem from './CategoryMenuItem/CategoryMenuItem';
import { mockCategory, mockCategoryList } from './mockData';

function CategoryMenu(props) {
    const { isMobile } = props;

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
                    {...props}
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

    const setContainer = (c) => {
        container = c;
    }

    return (
        <Transition
            in={props.in}
            timeout={2000}
            onEntering={() => { slide(container, 'slideDown', null); }}
            onEntered={() => { console.log(' entered') }}
            onExiting={() => { slide(container, 'slideUp', null); }}
            onExited={() => { console.log(' exited') }}>
            <ul ref={setContainer}>

                <CategoryMenuItem
                    {...props}
                    isMobile={isMobile}
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