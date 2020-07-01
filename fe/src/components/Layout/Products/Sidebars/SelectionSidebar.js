import React from 'react';
import { Sidebar } from './Sidebar';

function SelectionSidebar(props) {
    const { removeFacet, selectedFacets } = props;
    const items = [];

    console.log(selectedFacets)

    selectedFacets.map(f => {
        items.push({
            name: f.facetingName,
            code: f.id,
            type: f.type,
        });
   })

    return (
        <React.Fragment>
             <Sidebar
                filterType={"category"}
                items={items} 
                removeFacet={removeFacet}/>
        </React.Fragment>
    )
}

export default SelectionSidebar;