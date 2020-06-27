import React from 'react';
import { useSelector } from 'react-redux';
import { Sidebar } from './Sidebar';



function CategorySidebar(props) {


    const categories = useSelector(state => state.categories);

    console.log(categories);
    console.log(props);

    return (
        <Sidebar
            name={"category"} />
    )
}


export default CategorySidebar;