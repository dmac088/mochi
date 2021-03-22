import {
    GET_SHIPPING_DESTINATION_STARTED,
    GET_SHIPPING_DESTINATION_SUCCESS,
    GET_SHIPPING_DESTINATION_FAILURE,
} from "./ActionDestinations";

export const getShippingDestinationStarted = () => ({
    type: GET_SHIPPING_DESTINATION_STARTED,
    payload: {
        loading: true,
    }
});

export const getShippingDestinationSuccess = addressDestination => ({
    type: GET_SHIPPING_DESTINATION_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...addressDestination,
    }
});

export const getShippingDestinationFailure = error => ({
    type: GET_SHIPPING_DESTINATION_FAILURE,
    payload: {
        error,
        loading: false,
        isDone: false,
    }
});
