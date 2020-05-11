import axios from "axios";
import * as discoveryService from '../services/Discovery';
import { GET_SESSION } from "./ActionTypes";

export const getTokens = () => dispatch => {
  discoveryService.discoverAll()
    .then((response) => {
      axios.get(response.data._links.accessTokens.href)
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