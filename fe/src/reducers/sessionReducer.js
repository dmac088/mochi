import { GET_SESSION } from "../actions/ActionTypes";

const initialState = {
    username: null,
    password: null,
    tokens: {
      access_token: null,
      accessTokenExpiryDate: null,
      token_type: null,
      refresh_token: null,
      expires_in: null,
      scope: null,
      authenticated: false
    }
  };
  
  export default function(state = initialState, action) {
    switch (action.type) {
      case GET_SESSION:
        return {
          ...state,
          tokens: action.payload,
        }; 
      
      case SET_CREDENTIALS:
        return {
          ...state,
          username: action.username,
          password: action.password,
        };
        
      default:
        return state;
    }
  }