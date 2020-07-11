import React, {useState, useEffect, useRef} from 'react';
import { useSelector } from 'react-redux';
import { getCategoryPath } from '../../Helpers/Route/Route';
import { ButtonSidebar } from './Layout/ButtonSidebar';
import { findByCode } from '../../../../services/Category';
import { instance as axios } from "../../Helpers/api/axios";


function TagSidebar(props) {
   const { addFacet, selectedFacets, loading } = props;
   const items = [];
   const categories = useSelector(state => state.categories);
   const { categoryCode } = props.match.params;

    const [stateObject, setObjectState] = useState({
        tags: [],
    });

    const prevCategoryCode = usePrevious(categoryCode);

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    //this should be moved to a service class later on 
    const retrieveTags = (categoryCode, facets) => {
        const currentCategory = findByCode(categories.list, categoryCode);
        if(!currentCategory) { return; }
        axios.post(currentCategory._links.tags.href,
                    { facets: facets })
             .then((response) => {
                 setObjectState((prevState) => ({
                     ...prevState,
                     tags: (response.data._embedded) 
                            ? response.data._embedded.tagResources
                            : [],
                 }));
             });
    }

   //mapBrandsToSidebar
   stateObject.tags.filter(({data}) => !(selectedFacets).some(x => x.id === data.tagCode))
        .map(c => {
        items.push({
            display: c.data.tagDesc + ' (' + c.data.count + ')',
            code: c.data.tagCode,
            path: getCategoryPath(c.data.tagCode, props.match),
        });
   });

   useEffect(() => {
    if(categoryCode !== prevCategoryCode || !categories.loading || loading) {  
        retrieveTags(categoryCode, selectedFacets);
    }
   }, [categoryCode, categories.loading, loading]);

    return (
        <React.Fragment>
            {(items.length > 0)
            ? <ButtonSidebar
                filterType={"tag"}
                heading={"filter by tag"}
                items={items} 
                modFacet={addFacet}/>
            : <React.Fragment/>}
        </React.Fragment>
    )
}


export default TagSidebar;