import React from 'react';
import { ListSidebar } from './Layout/ListSidebar';

function SelectionSidebar(props) {
    const { removeFacet, selectedFacets } = props;

    return (
        <React.Fragment>
             <ListSidebar
                heading={"selection"}
                items={selectedFacets} 
                modFacet={removeFacet}/>
        </React.Fragment>
    )
}

export default SelectionSidebar;