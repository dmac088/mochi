import {
  GET_CUSTOMER_STARTED,
  GET_CUSTOMER_SUCCESS
} from "../actions/ActionTypes";

const initialState = {
    loading: false,
};

export default function (state = initialState, action) {
  
  switch (action.type) {

    case GET_CUSTOMER_STARTED:
      return {
        ...state,
        loading: true,
    }

    case GET_CUSTOMER_SUCCESS:
      return {
        ...state,
        ...action.payload,
        loading: false,
    }

    default:
      return state;
  }
}
