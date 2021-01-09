import {
    GET_SHIPPING_ADDRESS_STARTED,
    GET_SHIPPING_ADDRESS_SUCCESS,
    GET_SHIPPING_ADDRESS_FAILURE,
  } from "../actions/ActionTypes";
  
  const initialState = {
      loading: false,
      isDone: false,
      error: null,
  };
  
  export default function (state = initialState, action) {
  
    switch (action.type) {

      case GET_SHIPPING_ADDRESS_STARTED:
        return {
          ...state,
          ...action.payload,
      }
  
      case GET_SHIPPING_ADDRESS_SUCCESS:
        return {
          ...state,
          ...action.payload,
      }
  
      case GET_SHIPPING_ADDRESS_FAILURE:
        return {
          ...state,
          error: action.payload.error
      }
  
      default:
        return state;
    }
  }
   