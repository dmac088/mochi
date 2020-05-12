import { GET_ERROR } from "../actions/ActionTypes";

const initialState = {
    code: null,
    message: null,
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_ERROR:
      return {
        ...state,
        message: action.payload.message,
        code: action.payload.code,
      };

    default:
      return state;
  }
}
