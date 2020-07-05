import React, {useState, useEffect, useRef} from 'react';
import { useSelector } from 'react-redux';
import { getCategoryPath } from '../../Helpers/Route/Route';
import { Sidebar } from './Sidebar';
import { findByCode } from '../../../../services/Category';
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";


function CategorySidebar(props) {
   const { addFacet, selectedFacets, categoriesLoading } = props;
   const items = [];
   const categories = useSelector(state => state.categories);
   const { categoryCode } = props.match.params;

   const [stateObject, setObjectState] = useState({
        brands: [],
        loading: false,
    });

    const prevCategoryCode = usePrevious(categoryCode);
    const prevCategoriesLoading = usePrevious(categoriesLoading);

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    //this should be moved to a service class later on 
    const retrieveBrands = (categoryCode, facets) => {
        const currentCategory = findByCode(categories.list, categoryCode);
        if(!currentCategory) { return; }
        setObjectState((prevState) => ({
            ...prevState,
            loading: true,
        }));
        axios.post(currentCategory._links.brands.href,
                    { facets: facets })
             .then((response) => {
                 setObjectState((prevState) => ({
                     ...prevState,
                     brands: (response.data._embedded) 
                               ? response.data._embedded.brandResources
                               : [],
                     loading: false,
                 }));
             });
    }

   //mapBrandsToSidebar
   stateObject.brands.filter(({data}) => !selectedFacets.some(x => x.id === data.brandCode))
        .map(c => {
        items.push({
            display: c.data.brandDesc + ' (' + c.data.count + ')',
            code: c.data.brandCode,
            path: getCategoryPath(c.data.brandCode, props.match),
        });
   });

   useEffect(() => {
    if(categoryCode !== prevCategoryCode || categoriesLoading !== prevCategoriesLoading) {  
        retrieveBrands(categoryCode, selectedFacets);
    }
   }, [categoryCode, categoriesLoading]);

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