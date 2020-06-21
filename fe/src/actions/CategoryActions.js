import { instance as axios } from "../components/Layout/Helpers/api/axios";
import * as discoveryService from '../services/Discovery';
import {  GET_CATEGORIES_STARTED,
          GET_CATEGORIES_SUCCESS,
          GET_CATEGORIES_FAILURE } from "./ActionTypes";

export const getAllCategories = () => {
  return (dispatch, getState) => {

    dispatch(getCategoriesStarted());

    const state = getState();
    console.log(state);
    //const { href } = state.discovery.customer;

    discoveryService.discoverAll('en-GB', 'HKD')
    .then((response) => {
      axios.get(response.data._links.allCategories.href)
      .then((payload) => {
        return payload.data._embedded.categoryResources;
      }).then((categories) => {
        dispatch(getCategoriesSuccess(categories));
      }).catch((error) => {
        dispatch(getCategoriesFailure(error.response));
      });
    });
  }
}

const getCategoriesStarted = () => ({
  type: GET_CATEGORIES_STARTED
});

const getCategoriesSuccess = categories => ({
  type: GET_CATEGORIES_SUCCESS,
  payload: {
    list: categories,
    loading: false,
  }
});

const getCategoriesFailure = error => ({
  type: GET_CATEGORIES_FAILURE,
  payload: {
    error,
  }
});