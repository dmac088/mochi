import React, { useEffect, useState } from "react";
import { Transition } from 'react-transition-group';
import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';
import CategoryMenuItemSubList from './CategoryMenuItemSublist';

function CategoryMenuItem(props) {
    console.log(props);
    const { isMobile, isRoot, renderList, fullList, category, renderCategoryList, itemCounter } = props;
    const { childCount } = category.data;

    const [stateObject, setObjectState] = useState({
        hasChildren: childCount > 0,
        expand: !isMobile,
    });


    // const changeCategory = (e, routeProps) => {
    //     e.preventDefault();
    //     const { search } = location;
    //     routeProps.history.push(homeRouteString(routeProps.match) + '/category/' + e.currentTarget.id + search);
    // }

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

    const { hasChildren, expand } = stateObject;
    const children = [];
    let container = null;

    const setScope = (c) => {
        container = c;
    }

    const slide = (container, direction, params = { duration: 500 }) => {
        const element = ReactDOM.findDOMNode(container);
        if (element === undefined) { return; }
        Velocity(element, direction, params);
    }

    console.log("hasChildren = " + hasChildren);
    console.log("isMobile = " + isMobile);
    console.log("expand = " + expand);

    return (
        <li
            className={
                ((hasChildren)
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
                {(hasChildren && isMobile)
                    ? <span>
                        <i onClick={expandCat}
                            className={((!expand) ? "expand" : "") + " menu-expand"}>
                        </i>
                    </span>
                    : null
                }
            </a>
            <Transition
                in={(hasChildren && (expand || !isMobile))}
                timeout={2000}
                onEnter={() => { console.log('enter') }}
                onEntering={() => { slide(container, 'slideDown', null); }}
                onEntered={() => { console.log(' entered') }}
                onExit={() => { console.log(' exit') }}
                onExiting={() => { slide(container, 'slideUp', null); }}
                onExited={() => { console.log(' exited') }}>
                {/* <div ref={setScope}> */}
                <ul ref={setScope} className="category-mega-menu">
                    <CategoryMenuItemSubList
                        isMobile={isMobile}
                        renderList={renderList}
                        fullList={fullList}
                        children={getChildren(category, fullList, children)}
                        categoryLevel={category.data.categoryLevel}
                        itemCounter={itemCounter}
                        renderCategoryList={renderCategoryList} />
                </ul>
                {/* </div> */}
            </Transition>
        </li>
    )
}

export default CategoryMenuItem;
