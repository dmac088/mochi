import { GET_CATEGORIES } from "../actions/ActionTypes";

const initialState = {
  categories: [],
  category: {},
  links: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    //GET CAPABILITIES
    //OTHER REDUCER ACTIONS.
    case GET_CATEGORIES:
      return {
        ...state,
        categories: action.payload,
      };

    default:
      return state;
  }
}
