import { GET_SESSION, RESET_SESSION } from "../actions/ActionTypes";

const initialState = {  
    tokens: {}
  };
  
  export default function(state = initialState, action) {
    switch (action.type) {
      case GET_SESSION:
        return {
          ...state,
          tokens: action.payload,
        }; 
      case RESET_SESSION:
        return {
          tokens: initialState.tokens,
        }
      default:
        return state;
    }
  }