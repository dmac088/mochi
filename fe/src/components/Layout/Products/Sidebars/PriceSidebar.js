import React, { useEffect, useRef, useState } from 'react';
import { useSelector } from 'react-redux';
import { findByCode } from '../../../../services/Category';
import { RangeSidebar } from './Layout/RangeSidebar';
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";

function CategorySidebar(props) {
    const { addFacet, selectedFacets, loading } = props;
    const categories = useSelector(state => state.categories);
    const items = [];
    const { categoryCode } = props.match.params;
    const prevCategoryCode = usePrevious(categoryCode);

    const [stateObject, setObjectState] = useState({
        price: null,
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
        const currentCategory = findByCode(categories.list, categoryCode);
        if (!currentCategory) { return; }
        axios.post(currentCategory._links.maxPrice.href,
            { facets: facets })
            .then((response) => {
                setObjectState((prevState) => ({
                    ...prevState,
                    price: response.data,
                }));
            });
    }


    useEffect(() => {
        if (categoryCode !== prevCategoryCode || !categories.loading || loading) {
            retrieveMaxPrice(selectedFacets);
        }
    }, [categoryCode, categories.loading, loading]);


    items.push({
        display: stateObject.price,
        code: stateObject.price,
        path: null
    });


    return (
        <React.Fragment>
             <RangeSidebar
                    filterType={"price"}
                    heading={"filter by price"} />
        </React.Fragment>
    )
}


export default CategorySidebar;