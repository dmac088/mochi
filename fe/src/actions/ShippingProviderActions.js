import {
    GET_SHIPPING_PROVIDERS_STARTED,
    GET_SHIPPING_PROVIDERS_SUCCESS,
    GET_SHIPPING_PROVIDERS_FAILURE,
} from "./ActionTypes";

export const getShippingProviderStarted = () => ({
    type: GET_SHIPPING_PROVIDERS_STARTED,
    payload: {
        loading: true,
    }
});

export const getShippingProviderSuccess = addressProvider => ({
    type: GET_SHIPPING_PROVIDERS_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...addressProvider,
    }
});

export const getShippingProviderFailure = error => ({
    type: GET_SHIPPING_PROVIDERS_FAILURE,
    payload: {
        error,
        loading: false,
        isDone: false,
    }
});
