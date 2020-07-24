import { instance as axios } from "../components/Layout/Helpers/api/axios";

import {  GET_BAG_ITEMS_STARTED,
          GET_BAG_ITEMS_SUCCESS,
          GET_BAG_ITEMS_FAILURE } from "./ActionTypes";

export const getBagItems = (itemCodes = []) => {
  return (dispatch, getState) => {

    dispatch(getBagItemsStarted());

    const state = getState();
    axios.post(state.discovery.links.getProducts.href)
    .then((payload) => {
      return payload.data._embedded.categoryResources;
    }).then((items) => {
      dispatch(getBagItemsSuccess(items));
    }).catch((error) => {
     console.log(error);
      dispatch(getBagItemsFailure(error.response));
    });
  }
}

const getBagItemsStarted = () => ({
  type: GET_BAG_ITEMS_STARTED
});

const getBagItemsSuccess = items => ({
  type: GET_BAG_ITEMS_SUCCESS,
  payload: {
    items: items,
    loading: false,
  }
});

const getBagItemsFailure = error => ({
  type: GET_BAG_ITEMS_FAILURE,
  payload: {
    error,
  }
});