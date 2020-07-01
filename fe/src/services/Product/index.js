

export const newFacet = (facetId, facetName, display) => {
    return {
        "type": "EntityFacet",
        "facetingName": facetName,
        "id": facetId,
        "display": display,
    }
}