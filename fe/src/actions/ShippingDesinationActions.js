import {
    GET_SHIPPING_DESTINATIONS_STARTED,
    GET_SHIPPING_DESTINATIONS_SUCCESS,
    GET_SHIPPING_DESTINATIONS_FAILURE,
} from "./ActionTypes";

export const getShippingDestinationsStarted = () => ({
    type: GET_SHIPPING_DESTINATIONS_STARTED,
    payload: {
        loading: true,
    }
});

export const getShippingDestinationsSuccess = addressDestination => ({
    type: GET_SHIPPING_DESTINATIONS_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...addressDestination,
    }
});

export const getShippingDestinationsFailure = error => ({
    type: GET_SHIPPING_DESTINATIONS_FAILURE,
    payload: {
        error,
        loading: false,
        isDone: false,
    }
});
