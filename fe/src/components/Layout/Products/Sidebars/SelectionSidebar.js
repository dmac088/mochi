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

console.log(items);

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