import { instance as axios } from "../components/Layout/Helpers/api/axios";
import * as discoveryService from '../services/Discovery';
import { GET_CATEGORIES } from "./ActionTypes";

export const getAllCategories = () => dispatch => {
  discoveryService.discoverAll()
    .then((response) => {
      axios.get(response.data._links.allCategories.href)
        .then((payload) => {
          return payload.data._embedded.categoryResources;
        }).then((categories) => {
          dispatch({
            type: GET_CATEGORIES,
            payload: categories,
          });
        });
    });
}
