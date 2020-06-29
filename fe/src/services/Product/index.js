

export const newFacet = (facetId, facetName) => {
    return {
        "type": "EntityFacet",
        "facetingName": facetName,
        "id": facetId,
    }
}