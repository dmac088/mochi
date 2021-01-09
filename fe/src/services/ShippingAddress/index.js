import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import {
    getShippingAddressStarted,
    getShippingAddressSuccess,
    getShippingAddressFailure,
    updateShippingAddressStarted,
    updateShippingAddressSuccess,
    updateShippingAddressFailure,
} from "../../actions/ShippingAddressActions";

export const getAddress = (customer, addressTypeCode) => {
    return (dispatch) => {
        dispatch(getShippingAddressStarted());
        
        return axios.get(customer._links.address.href.replace('{addressTypeCode}', addressTypeCode))
            .then((payload) => {
                return payload.data;
            }).then((address) => {
                dispatch(getShippingAddressSuccess(address));
            }).catch((error) => {
                dispatch(getShippingAddressFailure(error.response));
            });
    }
}


export const updateAddress = (address, payload) => {
    return (dispatch) => {
        dispatch(updateShippingAddressStarted());
        console.log(address);
        return axios.post(  address._links.updateAddress.href,
                            payload)
        .then((payload) => {
            return payload.data;
        }).then((address) => {
            dispatch(updateShippingAddressSuccess(address));
        }).catch((error) => {
            dispatch(updateShippingAddressFailure(error.response));
        });
    }
}