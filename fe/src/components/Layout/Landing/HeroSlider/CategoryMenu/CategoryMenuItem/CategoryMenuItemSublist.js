import React, { useEffect } from 'react';
import { Transition } from 'react-transition-group';
import { slide } from '../../../../Helpers/Animation/Slide';

function CategoryMenuItemSubList(props) {

    const { isMobile, fullList, renderCategoryList, itemCounter, children, expand, hasChildren } = props;
    
    let container = null;
    const setScope = (c) => {
        container = c;
    }

    useEffect(() => {
        console.log("updating!");
        if(isMobile) { 
            slide(container, 'slideUp', { duration: 0 }); 
            return; 
        }
    }, []);

    const initMobStyle = {  "overflow": "hidden",
                            "marginTop": "0px",
                            "marginBottom": "0px",
                            "paddingTop": "0px", 
                            "paddingBottom": "0px"};
                            
    return (
        <ul className= {(hasChildren) 
                        ? "category-mega-menu"
                        : ""}
            ref={setScope}
            style={ (isMobile) ? initMobStyle : {} }>  
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
                        {renderCategoryList(isMobile, children, fullList, false, itemCounter)}
                    </React.Fragment>
            </Transition>
        </ul>
    )
}

export default CategoryMenuItemSubList;