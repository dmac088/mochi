import axios from "axios";
import * as discoveryService from '../services/Discovery';
import * as apiConfig from '../services/api'
import { GET_SESSION, SET_USERNAME, SET_PASSWORD } from "./ActionTypes";

export const authenticate = () => dispatch => {
  discoveryService.discoverAll()
    .then((response) => {
      console.log(response.data._links.accessTokens.href);
      axios({
              method: 'post',
              url: response.data._links.accessTokens.href,
             // data: apiConfig.formData,
             // headers: apiConfig.headers
            })
        .then((payload) => {
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
