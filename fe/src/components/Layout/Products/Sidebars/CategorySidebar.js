import React from 'react';
import { useSelector } from 'react-redux';
import { getCategoryPath } from '../../../../components/Layout/Helpers/Route/Route';
import { Sidebar } from './Sidebar';
import { findByCode, getChildren } from '../../../../services/Category';


function CategorySidebar(props) {
   const { addFacet } = props;
   const items = [];
   const categories = useSelector(state => state.categories);
   const { categoryCode } = props.match.params;
   const children = [];
   getChildren(findByCode(categories.list, categoryCode), categories.list, children);
   
   children.map(c => {
        items.push({
            name: c.data.categoryDesc + ' (' + c.data.count + ')',
            code: c.data.categoryCode,
            path: getCategoryPath(c.data.categoryCode, props.match),
        });
   })

    return (
        <React.Fragment>
            {(items.length > 0)
            ? <Sidebar
                filterType={"category"}
                items={items} 
                addFacet={addFacet}/>
            : <React.Fragment/>}
        </React.Fragment>
    )
}


export default CategorySidebar;