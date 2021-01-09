import {
    GET_BILLING_ADDRESS_STARTED,
    GET_BILLING_ADDRESS_SUCCESS,
    GET_BILLING_ADDRESS_FAILURE,
    UPDATE_BILLING_ADDRESS_STARTED,
    UPDATE_BILLING_ADDRESS_SUCCESS,
    UPDATE_BILLING_ADDRESS_FAILURE,
} from "./ActionTypes";

export const getBillingAddressStarted = () => ({
    type: GET_BILLING_ADDRESS_STARTED,
    payload: {
        loading: true,
    }
});

export const getBillingAddressSuccess = address => ({
    type: GET_BILLING_ADDRESS_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...address,
    }
});

export const getBillingAddressFailure = error => ({
    type: GET_BILLING_ADDRESS_FAILURE,
    payload: {
        error,
        loading: false,
        isDone: false,
    }
});

export const updateBillingAddressStarted = () => ({
    type: UPDATE_BILLING_ADDRESS_STARTED,
    payload: {
        loading: true,
    }
});

export const updateBillingAddressSuccess = address => ({
    type: UPDATE_BILLING_ADDRESS_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...address,
    }
});

export const updateBillingAddressFailure = error => ({
    type: UPDATE_BILLING_ADDRESS_FAILURE,
    payload: {
        error,
    }
});