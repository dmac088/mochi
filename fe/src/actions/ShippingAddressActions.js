import {
    GET_SHIPPING_ADDRESS_STARTED,
    GET_SHIPPING_ADDRESS_SUCCESS,
    GET_SHIPPING_ADDRESS_FAILURE,
    UPDATE_SHIPPING_ADDRESS_STARTED,
    UPDATE_SHIPPING_ADDRESS_SUCCESS,
    UPDATE_SHIPPING_ADDRESS_FAILURE,
} from "./ActionTypes";

export const getShippingAddressStarted = () => ({
    type: GET_SHIPPING_ADDRESS_STARTED,
    payload: {
        loading: true,
    }
});

export const getShippingAddressSuccess = address => ({
    type: GET_SHIPPING_ADDRESS_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...address,
    }
});

export const getShippingAddressFailure = error => ({
    type: GET_SHIPPING_ADDRESS_FAILURE,
    payload: {
        error,
        loading: false,
        isDone: false,
    }
});

export const updateShippingAddressStarted = () => ({
    type: UPDATE_SHIPPING_ADDRESS_STARTED,
    payload: {
        loading: true,
    }
});

export const updateShippingAddressSuccess = address => ({
    type: UPDATE_SHIPPING_ADDRESS_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...address,
    }
});

export const updateShippingAddressFailure = error => ({
    type: UPDATE_SHIPPING_ADDRESS_FAILURE,
    payload: {
        error,
    }
});