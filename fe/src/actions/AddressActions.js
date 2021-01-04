import {
    GET_ADDRESS_STARTED,
    GET_ADDRESS_SUCCESS,
    GET_ADDRESS_FAILURE
} from "./ActionTypes";

export const getAddressStarted = () => ({
    type: GET_ADDRESS_STARTED
});

export const getAddressSuccess = address => ({
    type: GET_ADDRESS_SUCCESS,
    payload: {
        loading: false,
        ...address,
    }
});

export const regAddressFailure = error => ({
    type: GET_ADDRESS_FAILURE,
    payload: {
        error,
    }
});