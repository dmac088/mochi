import axios from "axios";
import * as discoveryService from '../services/Discovery';
import { GET_SESSION, SET_USERNAME, SET_PASSWORD } from "./ActionTypes";

export const getTokens = () => dispatch => {
  discoveryService.discoverAll()
    .then((response) => {
      axios.post(response.data._links.accessTokens.href)
        .then((payload) => {
          return payload;
        }).then((tokens) => {
          dispatch({
            type: GET_SESSION,
            payload: tokens,
          });
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