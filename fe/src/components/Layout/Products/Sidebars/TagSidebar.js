import React, { useState, useEffect, useRef } from 'react';
import { useSelector } from 'react-redux';
import { ButtonSidebar } from './Layout/ButtonSidebar';
import { findByCode } from '../../../../services/Category';
import { instance as axios } from "../../Helpers/api/axios";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';

function TagSidebar(props) {
    const { type, addFacet, selectedFacets, loading, facets } = props;
    const categories = useSelector(state => state.categories);
    const { categoryCode } = props.match.params;

    const [stateObject, setObjectState] = useState({
        tagFacets: [],
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
            axios.post(currentCategory._links.tagFacets.href, (type === 'browse')
                                                              ? selectedFacets.map(f => f.data)
                                                              : [])
                .then((response) => {
                    return response.data._embedded;
                }).then((payload) => {
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
                            ...prevState,
                            tagFacets: (payload)
                                ? payload.tags
                                : [],
                        }));
                    }
                });
            return () => (isSubscribed = false);
        }
    }, [categoryCode, categories.loading, loading]);

    return (
        <React.Fragment>
            {(loading || categories.loading)
                ? <Spinner />
                : <ButtonSidebar
                    filterType={"tag"}
                    heading={"filter by tag"}
                    items={(type === 'browse') ? stateObject.tagFacets.filter(({ data }) => !selectedFacets.some(x => x.data.id === data.id)) : facets}
                    modFacet={addFacet} />}
        </React.Fragment>
    )
}


export default TagSidebar;