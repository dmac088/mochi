
import {
  GET_BAG_STARTED,
  GET_BAG_SUCCESS,
  GET_BAG_FAILURE,
  EMPTY_BAG,
} from "./ActionTypes";

export const emptyBag = () => ({
  type: EMPTY_BAG
});

export const getBagStarted = () => ({
  type: GET_BAG_STARTED,
  payload: {
    loading: true,
    isDone: false,
  }
});

export const getBagSuccess = bag => ({
  type: GET_BAG_SUCCESS,
  payload: {
    bag: bag.data,
    links: bag._links,
    loading: false,
    isDone: true,
  }
});

export const getBagFailure = error => ({
  type: GET_BAG_FAILURE,
  payload: {
    error,
    loading: false,
    isDone: false,
  }
});



