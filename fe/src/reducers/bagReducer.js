import {    ADD_BAG_ITEM, 
            UPDATE_BAG_ITEM,
            REMOVE_BAG_ITEM,
            UPDATE_BAG_TOTALS} from "../actions/ActionTypes";

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
                items: [...state.items, action.item]
            };

        case UPDATE_BAG_ITEM:
            return {
                ...state,
                items: [...(state.items.filter(i => i !== action.item)), action.item]
            };

        case REMOVE_BAG_ITEM:
            return {
                ...state,
                items: state.items.filter(i => i !== action.item),
            };
            
        case UPDATE_BAG_TOTALS:
            return {
                ...state,
                totalItems: action.totalItems,
                totalAmount: action.totalAmount,
            };

        default:
            return state;
    }
}
