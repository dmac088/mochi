import React from 'react';
import { Transition } from 'react-transition-group';
import { slide } from '../../../../Helpers/Animation/Slide';

function CategoryMenuItemSubList(props) {

    const { fullList, renderCategoryList, itemCounter, children, expand, hasChildren } = props;
    
    let container = null;
    const setScope = (c) => {
        container = c;
    }
    

    return (
        <ul className= {(hasChildren) 
                        ? "category-mega-menu"
                        : ""}
            ref={setScope}>  
            <Transition
                in={expand}
                timeout={0}
                onEntering={() => { console.log("Entering");
                                    slide(container, 'slideDown', { duration: 500 });
                }}
                onExiting={() => {  console.log("Exiting");
                                    slide(container, 'slideUp', { duration: 500 });        
                }}>
                <React.Fragment>
                    {renderCategoryList(false, children, fullList, false, itemCounter)}
                </React.Fragment>
            </Transition>
        </ul>
    )
}

export default CategoryMenuItemSubList;