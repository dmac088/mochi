import {
    GET_BAG_STARTED,
    GET_BAG_SUCCESS,
    GET_BAG_FAILURE,
    EMPTY_BAG,
} from "../actions/ActionTypes";

const initialState = {
    loading: false,
    isDone: false,
    error: null,
};

export default function (state = initialState, action) {
    switch (action.type) {
        case EMPTY_BAG:
            return {
                ...initialState,
            }

        case GET_BAG_STARTED:
            return {
                ...state,
                loading:        action.payload.loading,
                isDone:         action.payload.isDone,
            }

        case GET_BAG_SUCCESS:
            return {
                ...state,
                ...action.payload.bag,
            }

        case GET_BAG_FAILURE:
            return {
                ...state,
                error:          action.payload.error,
                loading:        action.payload.loading,
                isDone:         action.payload.isDone,
            }

        default:
            return state;
    }
}
