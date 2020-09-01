import {  GET_CATEGORIES_STARTED,
          GET_CATEGORIES_SUCCESS,
          GET_CATEGORIES_FAILURE } from "./ActionTypes";

export const getCategoriesStarted = () => ({
  type: GET_CATEGORIES_STARTED
});

export const getCategoriesSuccess = categories => ({
  type: GET_CATEGORIES_SUCCESS,
  payload: {
    list: categories,
    loading: false,
  }
});

export const getCategoriesFailure = error => ({
  type: GET_CATEGORIES_FAILURE,
  payload: {
    error,
  }
});