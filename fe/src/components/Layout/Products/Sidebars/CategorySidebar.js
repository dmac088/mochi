import React from 'react';
import { useSelector } from 'react-redux';
import { Sidebar } from './Sidebar';
import { findByCode, getChildren } from '../../../../services/Category';


function CategorySidebar(props) {

   const items = [];
   const categories = useSelector(state => state.categories);
   const { categoryCode } = props.match.params;
   const children = [];
   getChildren(findByCode(categories.list, categoryCode), categories.list, children);
   
   children.map(c => {
        items.push({
            name: c.data.categoryDesc + ' (' + c.data.count + ')',
        });
   })

    return (
        <Sidebar
            filterType={"category"}
            items={items} />
    )
}


export default CategorySidebar;