import React, { useEffect, useRef, useState } from 'react';
import { useSelector } from 'react-redux';
import { findByCode } from '../../../../services/Category';
import { RangeSidebar } from './Layout/RangeSidebar';
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';

function PriceSidebar(props) {
    const { addFacet, selectedFacets, loading, type, facets } = props;
    const categories = useSelector(state => state.categories);
    const { categoryCode } = props.match.params;
    const prevCategoryCode = usePrevious(categoryCode);

    console.log(type);
    console.log(facets);

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

    useEffect(() => {
        let isSubscribed = true;
        if (categoryCode !== prevCategoryCode || !categories.loading || loading) {
            const currentCategory = findByCode(categories.list, categoryCode);
            if (!currentCategory) { return; }
            axios.post(currentCategory._links.maxPriceFacet.href, [])
                .then((response) => {
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
                            ...prevState,
                            maxPrice: response.data.data.value,
                            currentPrice: (prevState.currentPrice) ? prevState.currentPrice : response.data.data.value,
                        }));
                    }
                });
        }
        return () => (isSubscribed = false);
    }, [categoryCode, categories.loading, loading]);

    const changePrice = (newPrice) => {
        setObjectState((prevState) => ({
            ...prevState,
            currentPrice: newPrice,
        }));
        addFacet({
                data: {
                    type: 'EntityFacet',
                    id: newPrice,
                    desc: `price <= ${newPrice}`,
                    value: newPrice, 
                    facetingName: "price", 
                    count: 0,                   
                }
        });
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


export default PriceSidebar;