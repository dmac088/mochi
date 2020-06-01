import React, { useEffect } from 'react';
import { Transition } from 'react-transition-group';
import { slide } from '../../../../Helpers/Animation/Slide';

function CategoryMenuItemSubList(props) {

    const { isMobile, fullList, renderCategoryList, itemCounter, children, expand, hasChildren } = props;
    
    let container = null;
    const setScope = (c) => {
        container = c;
    }

    return (
        <ul className= {(hasChildren) 
                        ? "category-mega-menu"
                        : ""}
            style={{display:"none"}}
            ref={setScope}>  
            <Transition
                in={expand}
                timeout={0}
                onEntering={() => { console.log("Entering");
                                    slide(container, 'slideDown', { duration: 500 , display:""}); 
                }}
                onExiting={() => {  console.log("Exiting");
                                    slide(container, 'slideUp', { duration: 500 , display:"none"}); 
                }}>
                    <React.Fragment>
                        {renderCategoryList(isMobile, children, fullList, false, itemCounter)}
                    </React.Fragment>
            </Transition>
        </ul>
    )
}

export default CategoryMenuItemSubList;