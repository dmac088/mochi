import {
    GET_BAG_STARTED,
    GET_BAG_SUCCESS,
    GET_BAG_FAILURE,
    GET_BAG_ITEMS_STARTED,
    GET_BAG_ITEMS_SUCCESS,
    GET_BAG_ITEMS_FAILURE,
    REMOVE_BAG_ITEM_SUCCESS
} from "../actions/ActionTypes";
import * as bagService from '../services/Bag/index';

const initialState = {
    items: [],
    bagStatusCode: null,
    totalItems: 0,
    totalQuantity: 0,
    links: null,
    loading: false,
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_BAG_STARTED:
            return {
                ...state,
                loading: true,
            }

        case GET_BAG_SUCCESS:
            return {
                ...state,
                bagStatusCode: action.payload.bag.bagStatusCode,
                totalItems: action.payload.bag.totalItems,
                totalQuantity: action.payload.bag.totalQuantity,
                links: action.payload.links,
                loading: false,
            }

        case GET_BAG_FAILURE:
            return {
                ...state,
                error: action.payload.error,
                loading: false,
            }

        case GET_BAG_ITEMS_STARTED:
            return {
                ...state,
                loading: true,
            }

        case GET_BAG_ITEMS_SUCCESS:
            return {
                ...state,
                items: action.payload.items || [],
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
                items: action.payload.items || [],
            }

        default:
            return state;
    }
}
