const initialState = {
    items: [],
    totalItems: 0,
    totalAmount: 0,
};

export default function (state = initialState, action) {

    switch (action.type) {
        case ADD_ITEM:
            return {

            };

        case UPDATE_ITEM:
            return {

            };

        case REMOVE_ITEM:
            return {

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


