import * as discoveryService from '../services/Discovery';
import  { GET_DISCOVERY_STARTED,
          GET_DISCOVERY_SUCCESS,
          GET_DISCOVERY_FAILURE
        } from "./ActionTypes";

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