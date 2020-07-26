import {
    ADD_BAG_ITEM,
    GET_BAG_ITEMS_STARTED,
    GET_BAG_ITEMS_SUCCESS,
    GET_BAG_ITEMS_FAILURE,
    REMOVE_BAG_ITEM_SUCCESS
} from "../actions/ActionTypes";
import * as bagService from '../services/Bag/index';

const initialState = {
    items: [],
    totalItems: 0,
    totalAmount: 0,
    loading: false,
};

export default function (state = initialState, action) {
    switch (action.type) {

        case GET_BAG_ITEMS_STARTED:
            return {
                ...state,
                loading: true,
            }

        case GET_BAG_ITEMS_SUCCESS:
            return {
                ...state,
                items: action.payload.items,
                totalItems: bagService.sumTotalItems(action.payload.items),
                totalAmount: bagService.sumTotalAmount(action.payload.items),
                loading: false,
            }

        case GET_BAG_ITEMS_FAILURE:
            return {
                ...state,
                error: action.payload.error,
                loading: false,
            }

        case REMOVE_BAG_ITEM_SUCCESS:
            const newItems = state.items.filter(i => i.data.productUPC !== action.payload.productCode);
            return {
                ...state,
                items: newItems,
                totalItems: bagService.sumTotalItems(newItems),
                totalAmount: bagService.sumTotalAmount(newItems),
            }

        default:
            return state;
    }
}
