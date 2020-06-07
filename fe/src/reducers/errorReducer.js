import { GET_ERROR } from "../actions/ActionTypes";

const initialState = { 
   
};
  
  export default function(state = initialState, action) {
    switch (action.type) {
      case GET_ERROR:
        return {
          ...state,
          ...action.payload,
        }; 
      default:
        return state;
    }
  }   