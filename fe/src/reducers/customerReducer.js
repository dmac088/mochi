import {
  GET_CUSTOMER,
  GET_CUSTOMER_STARTED,
  GET_CUSTOMER_FINISHED
} from "../actions/ActionTypes";

const initialState = {
  customer: {
    loading: false,
  },
};

export default function (state = initialState, action) {
  switch (action.type) {
    //GET CAPABILITIES
    //OTHER REDUCER ACTIONS.
    case GET_CUSTOMER_STARTED:
      return {
        ...state,
        customer: {
          ...state.customer,
          loading: true,
        }
      }
    case GET_CUSTOMER:
      return {
        ...state,
        customer: {
          ...state.customer,
          ...action.payload.customer
        }
      };
    case GET_CUSTOMER_FINISHED:
      return {
        ...state,
        customer: {
          ...state.customer,
          loading: true,
        }
      }

    default:
      return state;
  }
}