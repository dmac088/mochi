
import {
  GET_BAG_ITEMS_STARTED,
  GET_BAG_ITEMS_SUCCESS,
  GET_BAG_ITEMS_FAILURE
} from "./ActionTypes";

export const getBagItemsStarted = () => ({
  type: GET_BAG_ITEMS_STARTED
});

export const getBagItemsSuccess = items => ({
  type: GET_BAG_ITEMS_SUCCESS,
  payload: {
    items: items,
    loading: false,
  }
});

export const getBagItemsFailure = error => ({
  type: GET_BAG_ITEMS_FAILURE,
  payload: {
    error,
  }
});