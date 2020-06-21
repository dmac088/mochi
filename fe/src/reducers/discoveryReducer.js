import { GET_DISCOVERY_STARTED,
         GET_DISCOVERY_SUCCESS,
         GET_DISCOVERY_FAILURE } from "../actions/ActionTypes";

const initialState = {
  links: {}
};

export default function(state = initialState, action) {
  switch (action.type) {

    case GET_DISCOVERY_STARTED:
      return {
        ...state,
        loading: true,
      }

    case GET_DISCOVERY_SUCCESS:
      return {
        ...state,
        links: action.payload,
      };

    case GET_DISCOVERY_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error
      }

    default:
      return state;
  }
}
