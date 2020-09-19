import {
    
    GET_BAG_CONTENTS_STARTED,
    GET_BAG_CONTENTS_SUCCESS,
    GET_BAG_CONTENTS_FAILURE,
    REMOVE_BAG_ITEM_SUCCESS
} from "../actions/ActionTypes";

const initialState = {
    items: [],
    loading: false,
    isDone: false,
};

export default function (state = initialState, action) {
    switch (action.type) {
        
        case GET_BAG_CONTENTS_STARTED:
            return {
                ...state,
                loading: action.payload.loading,
                isDone: action.payload.isDone,
            }

        case GET_BAG_CONTENTS_SUCCESS:
            return {
                ...state,
                items: action.payload.items || [],
                loading: action.payload.loading,
                isDone: action.payload.isDone,
            }

        case GET_BAG_CONTENTS_FAILURE:
            return {
                ...state,
                error: action.payload.error,
                loading: action.payload.loading,
                isDone: action.payload.isDone,
            }

        case REMOVE_BAG_ITEM_SUCCESS:
            return {
                ...state,
                items: action.payload.items || [],
                loading: action.payload.loading,
                isDone: action.payload.isDone,
            }

        default:
            return state;
    }
}
