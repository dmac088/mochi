import {
  GET_CUSTOMER_STARTED,
  GET_CUSTOMER_SUCCESS,
  GET_CUSTOMER_FAILURE,
  REGISTER_CUSTOMER_STARTED,
  REGISTER_CUSTOMER_SUCCESS,
  REGISTER_CUSTOMER_FAILURE,
} from "../actions/ActionTypes";

const initialState = {
    loading: false,
    error: null,
};

export default function (state = initialState, action) {

  switch (action.type) {

    case REGISTER_CUSTOMER_STARTED:
      return {
        ...state,
        loading: true,
    }

    case REGISTER_CUSTOMER_SUCCESS:
      return {
        ...state,
        ...action.payload,
        loading: false,
    }

    case REGISTER_CUSTOMER_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error
    }

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

    case GET_CUSTOMER_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error
    }

    default:
      return state;
  }
}
 