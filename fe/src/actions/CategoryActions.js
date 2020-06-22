import { instance as axios } from "../components/Layout/Helpers/api/axios";
import {  GET_CATEGORIES_STARTED,
          GET_CATEGORIES_SUCCESS,
          GET_CATEGORIES_FAILURE } from "./ActionTypes";

export const getAllCategories = () => {
  return (dispatch, getState) => {

    dispatch(getCategoriesStarted());

    const state = getState();
    if (state.discovery.loaded) {
      axios.get(state.discovery.href)
      .then((payload) => {
        return payload.data._embedded.categoryResources;
      }).then((categories) => {
        dispatch(getCategoriesSuccess(categories));
      }).catch((error) => {
        dispatch(getCategoriesFailure(error.response));
      });
    } else {
      
    } 
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