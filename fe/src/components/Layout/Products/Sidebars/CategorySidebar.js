import React from 'react';
import { useSelector } from 'react-redux';
import { getCategoryPath } from '../../../../components/Layout/Helpers/Route/Route';
import { Sidebar } from './Sidebar';
import { findByCode, getChildren } from '../../../../services/Category';


function CategorySidebar(props) {
   const { addFacet, selectedFacets } = props;
   const items = [];
   const categories = useSelector(state => state.categories);
   const { categoryCode } = props.match.params;
   const children = [];
   getChildren(findByCode(categories.list, categoryCode), categories.list, children);
   

   //mapCategoriesToSidebar
   children.filter(({data}) => !selectedFacets.some(x => x.id === data.categoryCode))
        .map(c => {
        items.push({
            display: c.data.categoryDesc + ' (' + c.data.count + ')',
            code: c.data.categoryCode,
            path: getCategoryPath(c.data.categoryCode, props.match),
        });
   })

    return (
        <React.Fragment>
            {(items.length > 0)
            ? <Sidebar
                filterType={"filter by category"}
                items={items} 
                modFacet={addFacet}/>
            : <React.Fragment/>}
        </React.Fragment>
    )
}


export default CategorySidebar;