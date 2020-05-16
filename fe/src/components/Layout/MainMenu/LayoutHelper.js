
//the menuCategories array
//defines which categories will be shown in the main menu
//including the sort order of such categories
//categories can be brand categories or product categories
//for categories with children we want to render the children hierarchically 
//in cascading menu items, brand are non hierarchical so we just want to display
//the brands within the brand category. Additionally we need an API url in our 
//HATEOAS implementation to get all the ancestors of a particular productCategory 
//code
export const menuCategories = 
[
    'FRT01', //fruit
    'VEG02', //vegetables
    'FBR01', //featured brands
];