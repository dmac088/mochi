import { GET_CUSTOMER } from "../actions/ActionTypes";

const initialState = {
  customer: {},
};

export default function(state = initialState, action) {
  switch (action.type) {
    //GET CAPABILITIES
    //OTHER REDUCER ACTIONS.
    case GET_CUSTOMER:
      return {
        ...state,
        customer: action.payload,
      };

    default:
      return state;
  }
}