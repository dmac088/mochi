import { GET_SESSION_STARTED, 
         GET_SESSION_SUCCESS,
         GET_SESSION_FAILURE,
         RESET_SESSION} from "../actions/ActionTypes";

const initialState = { 
  loading: false,
  error: null,
  authenticated: false,
};
  
  export default function(state = initialState, action) {
    switch (action.type) {

    case GET_SESSION_STARTED:
      return {
        ...state,
        loading: true,
      }

    case GET_SESSION_SUCCESS:
      return {
        ...state,
        ...action.payload,
        loading: false,
      }

    case GET_SESSION_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error
      }

    case RESET_SESSION:
      return {
        ...initialState.tokens,
        loading: false,
        error: null,
        authenticated: false,
      }

      default:
        return state;
      }
  }