import {
    GET_BAG_STARTED,
    GET_BAG_SUCCESS,
    GET_BAG_FAILURE,
} from "../actions/ActionTypes";

const initialState = {
    bagStatusCode: null,
    totalItems: 0,
    totalQuantity: 0,
    links: null,
    loading: false,
    isDone: false,
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
                bagStatusCode:  action.payload.bag.bagStatusCode,
                totalItems:     action.payload.bag.totalItems,
                totalQuantity:  action.payload.bag.totalQuantity,
                links:          action.payload.links,
                loading: false,
                isDone: true,
            }

        case GET_BAG_FAILURE:
            return {
                ...state,
                error: action.payload.error,
                loading: false,
            }

        default:
            return state;
    }
}
