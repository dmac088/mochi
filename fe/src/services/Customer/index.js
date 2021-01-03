
import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import {
  regCustomerStarted,
  regCustomerSuccess,
  regCustomerFailure,
  getCustomerStarted,
  getCustomerSuccess,
  getCustomerFailure
} from '../../actions/CustomerActions'
import { authenticate } from '../Session';

export const findByUserName = () => {
  return (dispatch, getState) => {
    const state = getState();
    const { userName } = state.session;
    const { href } = state.discovery.links.getCustomer;
    
    dispatch(getCustomerStarted());

    axios.get(href.replace('{username}', userName))
      .then((response) => {
        dispatch(getCustomerSuccess(response.data));
      }).catch((error) => {
        dispatch(getCustomerFailure(error.response));
      });
  }
}

export const register = customer => {
  return (dispatch, getState) => {

    const state = getState();
    const { href } = state.discovery.links.registerCustomer;

    dispatch(regCustomerStarted());

    axios.post(href, customer)
      .then(() => {
        dispatch(regCustomerSuccess(customer));
      })
      .then(() => {
        dispatch(authenticate(customer.userName, customer.password));
      })
      .catch((error) => {
        dispatch(regCustomerFailure(error.response));
      });
  }
}