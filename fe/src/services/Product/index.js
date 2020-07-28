

export const newEntityFacet = (facetId, facetName, display) => {
    return {
        "type": "EntityFacet",
        "facetingName": facetName,
        "id": facetId,
        "display": display,
    }
}

export const newSearchFacet = (facetId, facetName, display) => {
    return {
        "type": "SearchFacet",
        "facetingName": facetName,
        "display": display,
        "value": facetId,
    }
}