
import axios from "axios";
import { GET_CUSTOMER, GET_ERROR } from "./ActionTypes";

export const findByUserName = (access_token, url, userName) => dispatch => {  
  axios({
    method: 'get',
    url: url.replace('{username}', userName),
    headers: {'Authorization': 'Bearer ' + access_token}
  }).then((payload) => {
    return payload.data;
  }).then((customer) => {
    dispatch({
      type: GET_CUSTOMER,
      payload: customer,
    });
  }).catch((error) => {
    dispatch({
      type: GET_ERROR,
      payload: error.response,
    });
  });
}
