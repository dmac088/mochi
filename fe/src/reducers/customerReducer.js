import {
  GET_CUSTOMER,
  GET_CUSTOMER_STARTED,
  GET_CUSTOMER_SUCCESS
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
    case GET_CUSTOMER_SUCCESS:
      return {
        ...state,
        customer: {
          ...state.customer,
          loading: false,
        }
      }

    default:
      return state;
  }
}
