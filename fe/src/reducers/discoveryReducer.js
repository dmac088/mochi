import { GET_DISCOVERY } from "../actions/ActionTypes";

const initialState = {
  links: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    //GET CAPABILITIES
    //OTHER REDUCER ACTIONS.
    case GET_DISCOVERY:
      return {
        ...state,
        links: action.payload,
      };

    default:
      return state;
  }
}
