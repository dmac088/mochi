import axios from "axios";
import { GET_CUSTOMER_STARTED, 
         GET_CUSTOMER_SUCCESS,
         GET_CUSTOMER_FAILURE } from "./ActionTypes";

export const findByUserName = dispatch => {  
  return (dispatch, getState) => {

    const state = getState();
    const { userName, access_token } = state.session;
    const { href } = state.discovery.links.customer;

    dispatch(getCustomerStarted());

    axios({
      method: 'get',
      url: href.replace('{username}', userName),
      headers: {'Authorization': 'Bearer ' + access_token}
    }).then((response) => {
      console.log(response);
      dispatch(getCustomerSuccess(response.data));
    }).catch((error) => {
       dispatch(getCustomerFailure(error.response));
    });
  }
}

const getCustomerStarted = () => ({
  type: GET_CUSTOMER_STARTED
});

const getCustomerSuccess = customer => ({
  type: GET_CUSTOMER_SUCCESS,
  payload: {
      ...customer,
      loading: false,
    }
});

const getCustomerFailure = error => ({
  type: GET_CUSTOMER_FAILURE,
  payload: {
      error,
  }
});