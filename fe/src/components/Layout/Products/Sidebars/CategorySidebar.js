import React from 'react';
import { useSelector } from 'react-redux';
import { Sidebar } from './Sidebar';
import { findByDesc, getChildren } from '../../../../services/Category';


function CategorySidebar(props) {


   const categories = useSelector(state => state.categories);

   console.log(categories);
   const { categoryDesc } = props.match.params;
   const children = [];
   console.log(getChildren(findByDesc(categories.list, categoryDesc), categories.list, children));

    return (
        <Sidebar
            name={"category"} />
    )
}


export default CategorySidebar;