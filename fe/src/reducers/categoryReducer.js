import { GET_CATEGORIES_STARTED,
         GET_CATEGORIES_SUCCESS,
         GET_CATEGORIES_FAILURE } from "../actions/ActionTypes";

  const initialState = {
    list: [] ,
    loading: false,
    isDone: false,
    error: null,
  };

  export default function(state = initialState, action) {
    switch (action.type) {
      case GET_CATEGORIES_STARTED:
        return {
          ...state,
          loading: true,
        }

      case GET_CATEGORIES_SUCCESS:
        return {
          ...state,
          ...action.payload,
          loading: false,
          isDone: true,
        };

      case GET_CATEGORIES_FAILURE:
        return {
          ...state,
          loading: false,
          error: action.payload.error
        }

      default:
        return state;
    }
  }
