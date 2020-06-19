import { GET_CATEGORIES_SUCCESS } from "../actions/ActionTypes";

const initialState = {
  categories: [],
  category: {},
  links: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    //GET CAPABILITIES
    //OTHER REDUCER ACTIONS.
    case GET_CATEGORIES_SUCCESS:
      return {
        ...state,
        categories: action.payload,
      };

    default:
      return state;
  }
}
