
import {
  GET_BAG_STARTED,
  GET_BAG_SUCCESS,
  GET_BAG_FAILURE,
} from "./ActionTypes";

export const getBagStarted = () => ({
  type: GET_BAG_STARTED
});

export const getBagSuccess = bag => ({
  type: GET_BAG_SUCCESS,
  payload: {
    bag: bag.data,
    links: bag._links,
    loading: false,
  }
});

export const getBagFailure = error => ({
  type: GET_BAG_FAILURE,
  payload: {
    error,
  }
});


