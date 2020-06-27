import React from 'react';
import { useSelector } from 'react-redux';
import { Sidebar } from './Sidebar';
import { findByDesc, getChildren } from '../../../../services/Category';


function CategorySidebar(props) {

   const items = [];
   const categories = useSelector(state => state.categories);
   const { categoryDesc } = props.match.params;
   const children = [];
   getChildren(findByDesc(categories.list, categoryDesc), categories.list, children);
   
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