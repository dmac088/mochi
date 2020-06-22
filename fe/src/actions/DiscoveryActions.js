import * as discoveryService from '../services/Discovery';
import  { GET_DISCOVERY_STARTED,
          GET_DISCOVERY_SUCCESS,
          GET_DISCOVERY_FAILURE
        } from "./ActionTypes";
import { getAllCategories } from './CategoryActions'

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
    return (dispatch) => {
        
      return dispatch(discover())
      .then(() => {
          dispatch(getAllCategories());
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
      loaded: true,
    }
  });
  
  const getDiscoveryFailure = error => ({
    type: GET_DISCOVERY_FAILURE,
    payload: {
      error,
      loaded: false,
    }
  });