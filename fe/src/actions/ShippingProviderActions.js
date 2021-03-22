import {
    GET_SHIPPING_PROVIDER_STARTED,
    GET_SHIPPING_PROVIDER_SUCCESS,
    GET_SHIPPING_PROVIDER_FAILURE,
} from "./ActionProviders";

export const getShippingProviderStarted = () => ({
    type: GET_SHIPPING_PROVIDER_STARTED,
    payload: {
        loading: true,
    }
});

export const getShippingProviderSuccess = addressProvider => ({
    type: GET_SHIPPING_PROVIDER_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...addressProvider,
    }
});

export const getShippingProviderFailure = error => ({
    type: GET_SHIPPING_PROVIDER_FAILURE,
    payload: {
        error,
        loading: false,
        isDone: false,
    }
});
