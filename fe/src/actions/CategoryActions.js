import { instance as axios } from "../components/Layout/Helpers/api/axios";
import {  GET_CATEGORIES_STARTED,
          GET_CATEGORIES_SUCCESS,
          GET_CATEGORIES_FAILURE } from "./ActionTypes";

export const getAllCategories = () => {
  return (dispatch, getState) => {

    dispatch(getCategoriesStarted());

    const state = getState();
    axios.get(state.discovery.links.getAllProductCategories.href)
    .then((payload) => {
      return payload.data._embedded.categoryResources;
    }).then((categories) => {
      dispatch(getCategoriesSuccess(categories));
    }).catch((error) => {
     console.log(error);
      dispatch(getCategoriesFailure(error.response));
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