import React, {useEffect} from 'react';
import { Transition } from 'react-transition-group';
import { slide } from '../../../../Helpers/Animation/Slide';

function CategoryMenuItemSubList(props) {

    const { isMobile, fullList, renderCategoryList, itemCounter, children, expand } = props;
    
    let container = null;
    const setScope = (c) => {
        container = c;
    }

    return (
        <Transition
                in={(expand)}
                timeout={2000}
                onEntering={() => { slide(container, 'slideDown', {display:""}); }}
                onExiting={() => { slide(container, 'slideUp', {display:"none"}); }}>
            <ul ref={setScope} className="category-mega-menu" style={(isMobile) ? {display:"none"} : {display:""}}>
                {renderCategoryList(isMobile, children, fullList, false, itemCounter)} 
            </ul>                        
        </Transition>
    )
}

export default CategoryMenuItemSubList;