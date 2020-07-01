import React from 'react';
import { Sidebar } from './Sidebar';

function SelectionSidebar(props) {
    const { removeFacet, selectedFacets } = props;
    const items = [];
    
    //mapFacetsToSidebar
    selectedFacets.map(f => {
        items.push({
            display: f.display,
            code: f.id,
            path: f.type,
        });
   })

    return (
        <React.Fragment>
             <Sidebar
                heading={"selection"}
                items={items} 
                modFacet={removeFacet}/>
        </React.Fragment>
    )
}

export default SelectionSidebar;