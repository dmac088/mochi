import React, { useEffect, useRef, useState } from 'react';
import { useSelector } from 'react-redux';
import { getCategoryPath } from '../../../../components/Layout/Helpers/Route/Route';
import { ListSidebar } from './ListSidebar';
import { findByCode, getChildren } from '../../../../services/Category';


function CategorySidebar(props) {
    const { addFacet, selectedFacets, loading } = props;
    const categories = useSelector(state => state.categories);
    const { categoryCode } = props.match.params;
    const prevCategoryCode = usePrevious(categoryCode);

    const [stateObject, setObjectState] = useState({
        items: [],
    });

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    //mapCategoriesToSidebar
    const retrieveCategories = (children = [], facets) => {
        const items = [];
        children.filter(({ data }) => !facets.some(x => x.id === data.categoryCode))
            .map(c => {
                items.push({
                    display: c.data.categoryDesc + ' (' + c.data.count + ')',
                    code: c.data.categoryCode,
                    path: getCategoryPath(c.data.categoryCode, props.match)
                    });
            });
            setObjectState(() => ({
                items: items,
            })
        );
    }   

    useEffect(() => {
        if (categoryCode !== prevCategoryCode || !categories.loading || loading) {
            const children = [];
            getChildren(findByCode(categories.list, categoryCode), categories.list, children);
            retrieveCategories(children, selectedFacets);
        }
    }, [categoryCode, categories.loading, loading]);


    return (
        <React.Fragment>
            {(stateObject.items.length > 0)
                ? <ListSidebar
                    filterType={"category"}
                    heading={"filter by category"}
                    items={stateObject.items}
                    modFacet={addFacet} />
                : <React.Fragment />}
        </React.Fragment>
    )
}


export default CategorySidebar;