import React, { useEffect, useRef, useState } from 'react';
import { useSelector } from 'react-redux';
import { getCategoryPath } from '../../../../components/Layout/Helpers/Route/Route';
import { ListSidebar } from './ListSidebar';
import { findByCode, getChildren } from '../../../../services/Category';
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";


function CategorySidebar(props) {
    const { addFacet, selectedFacets, loading } = props;
    const categories = useSelector(state => state.categories);
    const items = [];
    const { categoryCode } = props.match.params;
    const prevCategoryCode = usePrevious(categoryCode);

    const [stateObject, setObjectState] = useState({
        categories: [],
    });

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    //mapCategoriesToSidebar
    const retrieveCategories = (facets) => {
        const currentCategory = findByCode(categories.list, categoryCode);
        if(!currentCategory) { return; }
        axios.post(currentCategory._links.children.href,
            { facets: facets })
             .then((response) => {
                 setObjectState((prevState) => ({
                     ...prevState,
                     categories: response.data._embedded.categoryResources,
                 }));
             });
    }


    useEffect(() => {
        if (categoryCode !== prevCategoryCode || !categories.loading || loading) {
            retrieveCategories(selectedFacets);
        }
    }, [categoryCode, categories.loading, loading]);

    stateObject.categories.filter(({ data }) => !selectedFacets.some(x => x.id === data.categoryCode))
    .map(c => {
        items.push({
            display: c.data.categoryDesc + ' (' + c.data.count + ')',
            code: c.data.categoryCode,
            path: getCategoryPath(c.data.categoryCode, props.match)
            });
    });
   
    return (
        <React.Fragment>
            {(items.length > 0)
                ? <ListSidebar
                    filterType={"category"}
                    heading={"filter by category"}
                    items={items}
                    modFacet={addFacet} />
                : <React.Fragment />}
        </React.Fragment>
    )
}


export default CategorySidebar;