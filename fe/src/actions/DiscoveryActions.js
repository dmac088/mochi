import axios  from "axios";
import { GET_DISCOVERY } from "./ActionTypes";

export const getDiscovery = () => async dispatch => {

    const res = await axios.get('https://localhost:8090/api/Discovery/en-GB/HKD/');

    console.log(res.data._links);

    dispatch({
      type: GET_DISCOVERY,
      payload: res.data._links,
    });
}
