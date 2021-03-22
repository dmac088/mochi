import {
    GET_SHIPPING_PROVIDER_STARTED,
    GET_SHIPPING_PROVIDER_SUCCESS,
    GET_SHIPPING_PROVIDER_FAILURE,
} from "../actions/ActionTypes";

const initialState = {
    loading: false,
    isDone: false,
    error: null,
};

export default function (state = initialState, action) {
  
    switch (action.type) {

      case GET_SHIPPING_PROVIDER_STARTED:
        return {
          ...state,
          ...action.payload,
      }
  
      case GET_SHIPPING_PROVIDER_SUCCESS:
        return {
          ...state,
          ...action.payload,
      }
  
      case GET_SHIPPING_PROVIDER_FAILURE:
        return {
          ...state,
          error: action.payload.error
      }
  
      default:
        return state;
    }
  }