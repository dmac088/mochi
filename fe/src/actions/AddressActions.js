import {
    GET_ADDRESS_STARTED,
    GET_ADDRESS_SUCCESS,
    GET_ADDRESS_FAILURE,
    UPDATE_ADDRESS_STARTED,
    UPDATE_ADDRESS_SUCCESS,
    UPDATE_ADDRESS_FAILURE,
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
        loading: false,
        isDone: false,
    }
});

export const updateAddressStarted = () => ({
    type: UPDATE_ADDRESS_STARTED,
    payload: {
        loading: true,
    }
});

export const updateAddressSuccess = address => ({
    type: UPDATE_ADDRESS_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...address,
    }
});

export const updateAddressFailure = error => ({
    type: UPDATE_ADDRESS_FAILURE,
    payload: {
        error,
    }
});