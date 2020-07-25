import { instance as axios } from "../components/Layout/Helpers/api/axios";
//import * as bagService from "../services/Bag/index"

import {
  GET_BAG_ITEMS_STARTED,
  GET_BAG_ITEMS_SUCCESS,
  GET_BAG_ITEMS_FAILURE
} from "./ActionTypes";

export const getBagItems = (bagItems) => {
  return (dispatch, getState) => {

    dispatch(getBagItemsStarted());
    const state = getState();
    //const bagItems = bagService.getItems();

    axios.post(state.discovery.links.getProducts.href, bagItems.map(i => i.productCode))
      .then((payload) => {
        return payload.data._embedded.productResources;
      }).then((products) => {
        const items = products.map(p => {
          return {
            ...p,
            'quantity': bagItems.filter(i => i.productCode === p.data.productUPC)[0].quantity,
          }
        });
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