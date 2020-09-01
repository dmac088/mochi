import * as discoveryService from '../services/Discovery';
import  { GET_DISCOVERY_STARTED,
          GET_DISCOVERY_SUCCESS,
          GET_DISCOVERY_FAILURE
        } from "./ActionTypes";
import { getAllCategories } from './CategoryActions'
import { getBag, getBagItems } from '../services/Bag/index'; 

  export const discover = () => { 
      return (dispatch) => {
        
        dispatch(getDiscoveryStarted());

        return discoveryService.discoverAll()
        .then((response) => {
          dispatch(getDiscoverySuccess(response.data._links));
        }).catch((error) => {
          dispatch(getDiscoveryFailure(error.response));
        });
      }
  }

  export const initialize = () => {
    return (dispatch, getState) => {
      return dispatch(discover())
      .then(() => {
        dispatch(getAllCategories())
        .then(() => {
          dispatch(getBag())
          .then(() => {
            dispatch(getBagItems(getState().bag));
          })
        });
      });
    }
  }

  const getDiscoveryStarted = () => ({
    type: GET_DISCOVERY_STARTED
  });
  
  const getDiscoverySuccess = links => ({
    type: GET_DISCOVERY_SUCCESS,
    payload: {
      ...links,
      loading: false,
    }
  });
  
  const getDiscoveryFailure = error => ({
    type: GET_DISCOVERY_FAILURE,
    payload: {
      error,
    }
  });