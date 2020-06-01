import React, { useState, useEffect } from "react";
import { Transition } from 'react-transition-group';
import { slide } from '../../../../Helpers/Animation/Slide';
import CategoryMenuItemSubList from './CategoryMenuItemSublist';

function CategoryMenuItem(props) {
    const { isMobile, isRoot, renderList, fullList, category, renderCategoryList, itemCounter } = props;
    const { childCount } = category.data;

    const [stateObject, setObjectState] = useState({
        hasChildren: childCount > 0,
        expand: false,
    });

    const expandCat = (e) => {
        e.preventDefault();
        setObjectState((prevState) => ({
            ...prevState,
            expand: !prevState.expand,
        }));
    }

    const getIndent = (level, offset = 0) => {
        return ((level === 1)
            ? 25
            : 25 + (((level - 1) * 10) - offset)) + "px";
    }

    const getChildren = (parent, categories, children) => {
        const c = categories._embedded.categoryResources.filter(o => o.data.parentCode === parent.data.categoryCode);
        if (c.length === 0) {
            return children;
        }
        c.map((child) => {
            children.push(child);
            getChildren(child, categories, children);
        });
        return c;
    }

    const children = [];

    let container = null;
    const setScope = (c) => {
        container = c;
    }

     useEffect(() => {
        slide(container, 'slideUp', {display:"none"}); 
     }, []);

    return (
        <li
            className={
                ((stateObject.hasChildren)
                    ? "menu-item-has-children"
                    : "")
                +
                ((itemCounter > 8)
                    ? " hidden"
                    : "")
            }
            style={
                (isRoot && itemCounter > 8
                    // && !showMore
                )
                    ? { "display": "none" }
                    : { "--my-left-indent": getIndent(category.data.categoryLevel, 10) }
            }>
            <a id={category.data.categoryDesc}
                onClick={(e) => {
                    if ((e.target.tagName.toLowerCase() === "i")) { return }
                    //changeCategory(e, routeProps)
                }}
                className={"megamenu-head"}
                style={(isMobile)
                    ? { "--my-cat-indent": getIndent(category.data.categoryLevel) }
                    : { "": "" }}
                href="shop-left-sidebar.html">
                {category.data.categoryDesc}
                {(stateObject.hasChildren && isMobile)
                    ? <span>
                        <i onClick={expandCat}
                           className={((!stateObject.expand) ? "expand" : "") + " menu-expand"}>
                        </i>
                    </span>
                    : null
                }
            </a>
            <ul ref={setScope} className="category-mega-menu">
                <Transition
                    in={(stateObject.expand)}
                    timeout={2000}
                    onEntering={() => { slide(container, 'slideDown', {display:""}); }}
                    onExiting={() => { slide(container, 'slideUp', {display:"none"}); }}>
                    
                        <CategoryMenuItemSubList
                            isMobile={isMobile}
                            renderList={renderList}
                            fullList={fullList}
                            children={getChildren(category, fullList, children)}
                            categoryLevel={category.data.categoryLevel}
                            itemCounter={itemCounter}
                            renderCategoryList={renderCategoryList} />
                    
                </Transition>
            </ul>
        </li>
    )
}

export default CategoryMenuItem;
