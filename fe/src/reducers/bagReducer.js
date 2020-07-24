import {    ADD_BAG_ITEM, 
            UPDATE_BAG_ITEM,
            REMOVE_BAG_ITEM,
            UPDATE_BAG_TOTALS,
            GET_BAG_ITEMS_SUCCESS} from "../actions/ActionTypes";

const initialState = {
    items: [],
    totalItems: 0,
    totalAmount: 0,
};

export default function (state = initialState, action) {

    switch (action.type) {
        case ADD_BAG_ITEM:
            return {
                ...state,
                items: [...state.items, action.payload.item]
            };

        case UPDATE_BAG_ITEM:
            return {
                ...state,
                items: [...(state.items.filter(i => i !== action.payload.item)), action.payload.item]
            };

        case REMOVE_BAG_ITEM:
            return {
                ...state,
                items: state.items.filter(i => i !== action.payload.item),
            };
            
        case UPDATE_BAG_TOTALS:
            return {
                ...state,
                totalItems: action.payload.totalItems,
                totalAmount: action.payload.totalAmount,
            };

        case GET_BAG_ITEMS_SUCCESS: 
            return {
                ...state,
                items: action.payload.items,
            }

        default:
            return state;
    }
}
