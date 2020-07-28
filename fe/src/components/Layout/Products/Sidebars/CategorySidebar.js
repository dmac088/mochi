import React, { useEffect, useRef, useState } from 'react';
import { useSelector } from 'react-redux';
import { getCategoryPath } from '../../../../components/Layout/Helpers/Route/Route';
import { ListSidebar } from './Layout/ListSidebar';
import { findByCode } from '../../../../services/Category';
import { instance as axios } from "../../../Layout/Helpers/api/axios";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';


function CategorySidebar(props) {
    const { type, addFacet, selectedFacets, loading, facets } = props;
    const categories = useSelector(state => state.categories);
    const { categoryCode } = props.match.params;
    const prevCategoryCode = usePrevious(categoryCode);

    const [stateObject, setObjectState] = useState({
        displayFacets: [],
    });

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    useEffect(() => {
        let isSubscribed = true;
        if (type === 'browse' && (categoryCode !== prevCategoryCode || !categories.loading || loading)) {
            const currentCategory = findByCode(categories.list, categoryCode);
            if (!currentCategory) { return; }
            axios.post(currentCategory._links.children.href, [])
                .then((response) => {
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
                            ...prevState,
                            displayFacets: (response.data._embedded)
                                ? mapCategoriesToDisplayFacets(response.data._embedded.categoryResources, selectedFacets)
                                : [],
                            loading: false,
                        }));
                    }
                });
        }
        return () => (isSubscribed = false);
    }, [categoryCode, categories.loading, loading]);

    const mapCategoriesToDisplayFacets = (categories, selectedFacets) => {
        console.log(categories);
        return categories
            .filter(c => c.data.count > 0)
            .filter(({ data }) => !selectedFacets.some(x => x.id === data.categoryCode))
            .map(c => {
                return {
                    display: c.data.categoryDesc + ' (' + c.data.count + ')',
                    code: c.data.categoryCode,
                    path: getCategoryPath(c.data.categoryCode, props.match)
                }
            });

    }

    const mapSearchFacetsToDisplayFacets = (searchFacets, selectedFacets) => {
        console.log(searchFacets);
        return searchFacets
            .filter(({ data }) => !selectedFacets.some(x => x.id === data.id))
            .map(f => {
                return {
                    display: f.displayValue + ' (' + f.count + ')',
                    code: f.id,
                    path: getCategoryPath(f.id, props.match)
                }
            });
       
    }

    return (
        <React.Fragment>
            {(loading || categories.loading)
                ? <Spinner />
                : <ListSidebar
                    filterType={"category"}
                    heading={"filter by category"}
                    items={(type === 'browse') ? stateObject.displayFacets : mapSearchFacetsToDisplayFacets(facets, selectedFacets)}
                    modFacet={addFacet} />}
        </React.Fragment>
    )
}


export default CategorySidebar;