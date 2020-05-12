import axios from "axios";
import * as discoveryService from '../services/Discovery';
import * as apiConfig from '../services/api'
import { GET_SESSION, SET_USERNAME, SET_PASSWORD } from "./ActionTypes";

export const authenticate = () => dispatch => {
  discoveryService.discoverAll()
    .then((response) => {

      const form = new FormData();
      Object.keys(apiConfig.formData).forEach((key) => {
        form.append(key, apiConfig.formData[key])
      });
      
      axios.post(
        response.data._links.accessTokens.href,
        form,
        apiConfig.config,
        ).then((payload) => {
          return payload;
        }).then((tokens) => {
          dispatch({
            type: GET_SESSION,
            payload: tokens,
          });
        }).catch((error)=> {
          console.log(error);
      });
    });
}

export const setUsername = (event) => dispatch => {
  dispatch({
    type: SET_USERNAME,
    username: event.currentTarget.value,
  });
}

export const setPassword = (event) => dispatch => {
  dispatch({
    type: SET_PASSWORD,
    password: event.currentTarget.value,
  });
}
