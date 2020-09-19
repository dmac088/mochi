
import {
  GET_BAG_CONTENTS_STARTED,
  GET_BAG_CONTENTS_SUCCESS,
  GET_BAG_CONTENTS_FAILURE,
  ADD_BAG_ITEM_STARTED,
  ADD_BAG_ITEM_SUCCESS,
  ADD_BAG_ITEM_FAILURE,
  REMOVE_BAG_ITEM_STARTED,
  REMOVE_BAG_ITEM_SUCCESS,
  REMOVE_BAG_ITEM_FAILURE,
} from "./ActionTypes";

export const getBagContentsStarted = () => ({
  type: GET_BAG_CONTENTS_STARTED,
  payload: {
    loading: true,
    isDone: false,
  }
});

export const getBagContentsSuccess = items => ({
  type: GET_BAG_CONTENTS_SUCCESS,
  payload: {
    items: items,
    loading: false,
    isDone: true,
  }
});

export const getBagContentsFailure = error => ({
  type: GET_BAG_CONTENTS_FAILURE,
  payload: {
    error,
    loading: false,
    isDone: false,
  }
});

export const addBagItemStarted = () => ({
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


