import { GET_ERROR } from "../actions/ActionTypes";

const initialState = {
  details: {
    type: null,
    code: null,
    description: null,
    message: null,
  }
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_ERROR:
      return {
        ...state,
        details: action.payload,
      };

    default:
      return state;
  }
}
