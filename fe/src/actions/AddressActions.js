import {
    GET_ADDRESS_STARTED,
    GET_ADDRESS_SUCCESS,
    GET_ADDRESS_FAILURE
} from "./ActionTypes";

export const getAddressStarted = () => ({
    type: GET_ADDRESS_STARTED,
    payload: {
        loading: true,
    }
});

export const getAddressSuccess = address => ({
    type: GET_ADDRESS_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...address,
    }
});

export const getAddressFailure = error => ({
    type: GET_ADDRESS_FAILURE,
    payload: {
        error,
    }
});