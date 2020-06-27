import {
  GET_SESSION_STARTED,
  GET_SESSION_SUCCESS,
  GET_SESSION_FAILURE,
  RESET_SESSION
} from "./ActionTypes";
 
export const clearSession = () => ({
  type: RESET_SESSION
});

export const getSessionStarted = () => ({
  type: GET_SESSION_STARTED
});

export const getSessionSuccess = session => ({
  type: GET_SESSION_SUCCESS,
  payload: {
    ...session,
    loading: false,
  }
});

export const getSessionFailure = error => ({
  type: GET_SESSION_FAILURE,
  payload: {
    error,
  }
});
