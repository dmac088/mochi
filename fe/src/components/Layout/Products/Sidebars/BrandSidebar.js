import React, { useState, useEffect, useRef } from 'react';
import { useSelector } from 'react-redux';
import { ListSidebar } from './Layout/ListSidebar';
import { findByCode } from '../../../../services/Category';
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';


function BrandSidebar(props) {
    const { addFacet, selectedFacets, loading } = props;
    const items = [];
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
            axios.post(currentCategory._links.brands.href, selectedFacets)
                .then((response) => {
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
                            ...prevState,
                            brands: (response.data._embedded)
                                ? response.data._embedded.brandResources
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
                    items={items}
                    modFacet={addFacet} />}
        </React.Fragment>
    )
}


export default BrandSidebar;