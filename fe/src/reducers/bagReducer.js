import {
    ADD_BAG_ITEM,
    GET_BAG_ITEMS_STARTED,
    GET_BAG_ITEMS_SUCCESS,
    GET_BAG_ITEMS_FAILURE,
    REMOVE_BAG_ITEM_SUCCESS
} from "../actions/ActionTypes";

const initialState = {
    items: [],
    totalItems: 0,
    totalAmount: 0,
    loading: false,
};

export default function (state = initialState, action) {
    switch (action.type) {
        case ADD_BAG_ITEM:
            return {
                ...state,
                items: [...state.items, action.payload.item]
            };

        case GET_BAG_ITEMS_STARTED:
            return {
                ...state,
                loading: true,
            }

        case GET_BAG_ITEMS_SUCCESS:
            return {
                ...state,
                items: action.payload.items,
                loading: false,
            }

        case GET_BAG_ITEMS_FAILURE:
            return {
                ...state,
                error: action.payload.error,
                loading: false,
            }

        case REMOVE_BAG_ITEM_SUCCESS:
            return {
                ...state,
                items: state.items.filter(i => i.productCode !== action.payload.productCode),
            }

        default:
            return state;
    }
}
