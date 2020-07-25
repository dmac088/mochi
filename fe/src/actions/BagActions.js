
import {
  GET_BAG_ITEMS_STARTED,
  GET_BAG_ITEMS_SUCCESS,
  GET_BAG_ITEMS_FAILURE,
  ADD_BAG_ITEM_STARTED,
  ADD_BAG_ITEM_SUCCESS,
  ADD_BAG_ITEM_FAILURE,
  REMOVE_BAG_ITEM_STARTED,
  REMOVE_BAG_ITEM_SUCCESS,
  REMOVE_BAG_ITEM_FAILURE,
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

export const raddBagItemStarted = () => ({
  type: ADD_BAG_ITEM_STARTED
});

export const addBagItemSuccess = productCode => ({
  type: ADD_BAG_ITEM_SUCCESS,
  payload: {
    productCode: productCode,
  } 
});

export const addBagItemFailure = error => ({
  type: ADD_BAG_ITEM_FAILURE,
  payload: {
    error,
  }
});

export const removeBagItemStarted = () => ({
  type: REMOVE_BAG_ITEM_STARTED
});

export const removeBagItemSuccess = productCode => ({
  type: REMOVE_BAG_ITEM_SUCCESS,
  payload: {
    productCode: productCode,
  } 
});

export const removeBagItemFailure = error => ({
  type: REMOVE_BAG_ITEM_FAILURE,
  payload: {
    error,
  }
});


