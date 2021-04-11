import React, { useState, useEffect, useRef } from 'react';
import { useSelector } from 'react-redux';
import { ListSidebar } from './Layout/ListSidebar';
import { findByCode } from '../../../../services/Category';
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';


function BrandSidebar(props) {
    const { type, addFacet, selectedFacets, loading, facets } = props;
    const categories = useSelector(state => state.categories);
    const { categoryCode } = props.match.params;

    const [stateObject, setObjectState] = useState({
        brandFacets: [],
    });

    const prevCategoryCode = usePrevious(categoryCode);

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    useEffect(() => {
        let isSubscribed = true;
        if (categoryCode !== prevCategoryCode || !categories.loading || loading) {
            const currentCategory = findByCode(categories.list, categoryCode);
            if (!currentCategory) { return; }
            axios.post(currentCategory._links.brandFacets.href, (type === 'browse')
                                                                ? selectedFacets.map(f => f.data)
                                                                : [])
                .then((response) => {
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
                            ...prevState,
                            brandFacets: (response.data._embedded)
                                ? response.data._embedded.brandSearchFacetResources
                                : [],
                        }));
                    }
                });
        }
        return () => (isSubscribed = false);
    }, [categoryCode, categories.loading, loading]);

    return (
        <React.Fragment>
            {(loading || categories.loading)
                ? <Spinner />
                : <ListSidebar
                    filterType={"brand"}
                    heading={"filter by brand"}
                    items={(type === 'browse') ? stateObject.brandFacets.filter(({ data }) => !selectedFacets.some(x => x.data.id === data.id)) : facets}
                    modFacet={addFacet} />}
        </React.Fragment>
    )
}


export default BrandSidebar;