import axios from "axios";
import * as apiConfig from '../services/api'
import { GET_SESSION, RESET_SESSION, GET_ERROR } from "./ActionTypes";

export const authenticate = (username, password, url) => dispatch => {
  const form = new FormData();
  Object.keys(apiConfig.formData).forEach((key) => {
    form.append(key, apiConfig.formData[key])
  });

  form.append('username', username);
  form.append('password', password);

  console.log(username);
  console.log(url);

  axios.post(
    url,
    form,
    apiConfig.config,
  ).then((payload) => {
    return payload.data;
  }).then((tokens) => {
    dispatch({
      type: GET_SESSION,
      payload: tokens,
    });
  }).catch((error) => {
    console.log(error);
    dispatch({
      type: GET_ERROR,
      payload: error.response,
    });
  });
}

export const logout = () => dispatch => {
  dispatch({
    type: RESET_SESSION,
  });
}
