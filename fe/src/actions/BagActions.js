import { instance as axios } from "../components/Layout/Helpers/api/axios";
import * as bagService from "../services/Bag/index"

import {  GET_BAG_ITEMS_STARTED,
          GET_BAG_ITEMS_SUCCESS,
          GET_BAG_ITEMS_FAILURE } from "./ActionTypes";

export const getBagItems = () => {
  return (dispatch, getState) => {

    dispatch(getBagItemsStarted());
    const state = getState();

    axios.post(state.discovery.links.getProducts.href,
      bagService.getItems().map(x => x.productCode))
    .then((payload) => {
      return payload.data._embedded.productResources;
    }).then((items) => {
      dispatch(getBagItemsSuccess(items));
    }).catch((error) => {
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