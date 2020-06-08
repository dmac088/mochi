import axios from "axios";
import * as apiConfig from '../services/api'
import { GET_SESSION_STARTED, 
         GET_SESSION_SUCCESS,
         GET_SESSION_FAILURE,
         RESET_SESSION
        } from "./ActionTypes"; 

export const authenticate = (username, password) => {
  return (dispatch, getState) => {
    const state = getState();

    const { href } = state.discovery.links.session;

    const form = new FormData();
    Object.keys(apiConfig.formData).forEach((key) => {
      form.append(key, apiConfig.formData[key])
    });

    form.append('username', username);
    form.append('password', password);

    dispatch(getSessionStarted());

    axios.post(
      href,
      form,
      apiConfig.config,
    ).then((response) => {
      dispatch(getSessionSuccess(response.data));
    }).catch((error) => {
      dispatch(getSessionFailure(error.response));
    });
  }
}

export const logoutSession = () => {
  return (dispatch) => {
    dispatch(clearSession());
  }
}

const clearSession = () => ({
  type: RESET_SESSION
});

const getSessionStarted = () => ({
  type: GET_SESSION_STARTED
});

const getSessionSuccess = session => ({
  type: GET_SESSION_SUCCESS,
  payload: {
      ...session,
      loading: false,
    }
});

const getSessionFailure = error => ({
  type: GET_SESSION_FAILURE,
  payload: {
      error,
  }
});
