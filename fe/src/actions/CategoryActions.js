import axios  from "axios";
import { GET_CATEGORIES } from "./ActionTypes";

export const getAllCategories = () => async dispatch => {

    const res = await axios.get('https://localhost:8090/api/Category/en-GB/HKD/');

    console.log(res.data._embedded.categoryResources);

    dispatch({
      type: GET_CATEGORIES,
      payload: res.data._embedded.categoryResources,
    });
}
