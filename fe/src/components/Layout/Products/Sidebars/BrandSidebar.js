import React from 'react';
import { useSelector } from 'react-redux';
import { getCategoryPath } from '../../Helpers/Route/Route';
import { Sidebar } from './Sidebar';
import { findByCode, getChildren } from '../../../../services/Category';


function CategorySidebar(props) {
   const { addFacet, selectedFacets } = props;
   const items = [];
   const categories = useSelector(state => state.categories);
   const { categoryCode } = props.match.params;
   const children = [];
   getChildren(findByCode(categories.list, categoryCode), categories.list, children);
   

    //this should be moved to a service class later on 
    const retrieveBrands = (categoryCode, facets) => {
        
        // const currentCategory = findByCode(categories.list, categoryCode);
        //     if(!currentCategory) { return; }
        //     axios.post(currentCategory._links.products.href,
        //             { facets: facets })
        //     .then((response) => {
        //         setObjectState((prevState) => ({
        //             ...prevState,
        //             products: (response.data._embedded) 
        //                       ? response.data._embedded.productResources
        //                       : [],
        //             selectedFacets: facets,
        //             loading: false,
        //         }));
        //     });
    }

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
                filterType={"brand"}
                heading={"filter by brand"}
                items={items} 
                modFacet={addFacet}/>
            : <React.Fragment/>}
        </React.Fragment>
    )
}


export default CategorySidebar;