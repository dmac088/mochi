import {
    GET_SHIPPING_TYPE_STARTED,
    GET_SHIPPING_TYPE_SUCCESS,
    GET_SHIPPING_TYPE_FAILURE,
} from "./ActionTypes";

export const getShippingTypeStarted = () => ({
    type: GET_SHIPPING_TYPE_STARTED,
    payload: {
        loading: true,
    }
});

export const getShippingTypeSuccess = addressType => ({
    type: GET_SHIPPING_TYPE_SUCCESS,
    payload: {
        loading: false,
        isDone: true,
        ...addressType,
    }
});

export const getShippingTypeFailure = error => ({
    type: GET_SHIPPING_TYPE_FAILURE,
    payload: {
        error,
        loading: false,
        isDone: false,
    }
});
