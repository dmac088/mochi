import { GET_SESSION, RESET_SESSION } from "../actions/ActionTypes";

const initialState = { 
  loading: false,
  error: null,
};
  
  export default function(state = initialState, action) {
    switch (action.type) {
      case GET_SESSION:
        return {
          ...state,
          ...action.payload,
        }; 
      case RESET_SESSION:
        return {
          ...initialState.tokens,
        }
      default:
        return state;
    }
  }