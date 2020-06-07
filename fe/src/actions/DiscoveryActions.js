import * as discoveryService from '../services/Discovery';
import { GET_DISCOVERY } from "./ActionTypes";

export const discover = (lang, curr) => dispatch => { 
    return discoveryService.discoverAll(lang, curr)
    .then((response) => {
        dispatch({
            type: GET_DISCOVERY,
            payload: response.data._links,
        });
    });
}