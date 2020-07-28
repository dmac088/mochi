import React, { useEffect, useRef, useState } from 'react';
import { useSelector } from 'react-redux';
import { findByCode } from '../../../../services/Category';
import { RangeSidebar } from './Layout/RangeSidebar';
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';

function CategorySidebar(props) {
    const { addFacet, selectedFacets, loading } = props;
    const categories = useSelector(state => state.categories);
    const items = [];
    const { categoryCode } = props.match.params;
    const prevCategoryCode = usePrevious(categoryCode);

    const [stateObject, setObjectState] = useState({
        maxPrice: null,
        currentPrice: null,
    });

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    //mapCategoriesToSidebar
    const retrieveMaxPrice = (facets) => {

    }


    useEffect(() => {
        let isSubscribed = true;
        if (categoryCode !== prevCategoryCode || !categories.loading || loading) {
            const currentCategory = findByCode(categories.list, categoryCode);
            if (!currentCategory) { return; }
            axios.post(currentCategory._links.maxPrice.href, selectedFacets)
                .then((response) => {
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
                            ...prevState,
                            maxPrice: response.data,
                            currentPrice: (prevState.currentPrice) ? prevState.currentPrice : response.data,
                        }));
                    }
                });
        }
        return () => (isSubscribed = false);
    }, [categoryCode, categories.loading, loading]);


    items.push({
        display: stateObject.price,
        code: stateObject.price,
        path: null
    });

    const changePrice = (newPrice) => {
        setObjectState((prevState) => ({
            ...prevState,
            currentPrice: newPrice,
        }));
        addFacet(newPrice, "maxPrice", `price <= ${newPrice}`);
    }

    return (
        <React.Fragment>
            {(loading || categories.loading)
                ? <Spinner />
                : <RangeSidebar
                    filterType={"price"}
                    heading={"filter by price"}
                    maxPrice={stateObject.maxPrice}
                    currentPrice={stateObject.currentPrice}
                    changePrice={changePrice} />}
        </React.Fragment>
    )
}


export default CategorySidebar;