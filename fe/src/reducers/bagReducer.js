const initialState = {
    items: [],
    totalItems: 0,
    totalAmount: 0,
};

export default function (state = initialState, action) {

    switch (action.type) {
        case ADD_ITEM:
            return {
                ...state,
                items: [...state.items, action.item]
            };

        case UPDATE_ITEM:
            return {
                ...state,
                items: [...(state.items.filter(i => i !== action.item)), action.item]
            };

        case REMOVE_ITEM:
            return {
                ...state,
                items: state.items.filter(i => i !== action.item),
            };
            
        case UPDATE_CART_TOTALS:
            return {

            };

        case UPDATE_CART_ITEMS:
            return {

            };

        default:
            return state;
    }


