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
        categoryFacets: [],
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
            axios.post(currentCategory._links.childFacets.href, [])
                .then((response) => {
                    console.log(response);
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
                            ...prevState,
                            categoryFacets: (response.data._embedded)
                                ? response.data._embedded.categoryFacetResources
                                : [],
                            loading: false,
                        }));
                    }
                });
        }
        return () => (isSubscribed = false);
    }, [categoryCode, categories.loading, loading]);


    console.log(facets);
    return (
        <React.Fragment>
            {(loading || categories.loading)
                ? <Spinner />
                : <ListSidebar
                    filterType={"category"}
                    heading={"filter by category"}
                    items={(type === 'browse') ? stateObject.categoryFacets.filter(({ data }) => !selectedFacets.some(x => x.data.id === data.id)) : facets}
                    addFacet={addFacet} />}
        </React.Fragment>
    )
}


export default CategorySidebar;