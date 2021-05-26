import React, { useEffect, useRef, useState } from 'react';
import { useSelector } from 'react-redux';
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
        categories: [],
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
            axios.post(currentCategory._links.children.href, (type === 'browse')
                                                                ? selectedFacets.map(f => f.data)
                                                                : [])
                .then((response) => {
                    console.log(currentCategory._links.children.href);
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
                            ...prevState,
                            categories: (response.data._embedded)
                                ? response.data._embedded.categories
                                : [],
                            loading: false,
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
                    filterType={"category"}
                    heading={"filter by category"}
                    items={ (type === 'browse') 
                            ? stateObject.categories
                            .filter(c => c.data.count > 0)
                            .filter(({ data }) => !selectedFacets.some(x => x.data.id === data.id)) 
                            : facets}
                    modFacet={addFacet} />}
        </React.Fragment>
    )
}

export default CategorySidebar;