import React, { useState } from "react";
import MobileCategoryMenuItemSubList from './MobileCategoryMenuItemSublist';
import WebCategoryMenuItemSubList from './WebCategoryMenuItemSublist';
import { Link } from "react-router-dom";
import { getCategoryPath } from "../../../../Helpers/Route/Route";
import { getChildCategories } from "../../../../../../services/Category/index";

function CategoryMenuItem(props) {
    
    const { isMobile, isRoot, renderList, fullList, category, renderCategoryList, itemCounter, match } = props;

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

    return (
        <li
            className={((stateObject.hasChildren)
                ? "menu-item-has-children"
                : "") +
                ((itemCounter > 8)
                ? " hidden"
                : "")}
            style={
                (isRoot && itemCounter > 8)
                ? { "display": "none" }
                : { "--my-left-indent": getIndent(category.data.level, 10) }
            }>

            <Link 
                to={getCategoryPath(category.data.id, match)}
                style={(isMobile)
                    ? { "--my-cat-indent": getIndent(category.data.level) }
                    : { "": "" }}>
                    {category.data.desc} ({category.data.count})
                    {(stateObject.hasChildren && isMobile)
                    ? <span>
                        <i onClick={expandCat}
                            className={((!stateObject.expand) ? " expand" : "") + " menu-expand"}>
                        </i>
                      </span>
                    : null}
            </Link>
            
            {(isMobile) 
            ?   <MobileCategoryMenuItemSubList
                    renderList={renderList}
                    fullList={fullList}
                    children={getChildCategories(category, fullList, [])}
                    level={category.data.level}
                    itemCounter={itemCounter}
                    renderCategoryList={renderCategoryList}
                    expand={stateObject.expand}
                    hasChildren={stateObject.hasChildren} />
            :   <WebCategoryMenuItemSubList
                    renderList={renderList}
                    fullList={fullList}
                    children={getChildCategories(category, fullList, [])}
                    level={category.data.level}
                    itemCounter={itemCounter}
                    renderCategoryList={renderCategoryList}
                    expand={stateObject.expand}
                    hasChildren={stateObject.hasChildren} />}
        </li>
    )
}

export default CategoryMenuItem;
