import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import {
    getBillingAddressStarted,
    getBillingAddressSuccess,
    getBillingAddressFailure,
    updateBillingAddressStarted,
    updateBillingAddressSuccess,
    updateBillingAddressFailure,
} from "../../actions/BillingAddressActions";

export const getAddress = (customer, addressTypeCode) => {
    return (dispatch) => {
        dispatch(getBillingAddressStarted());
        
        return axios.get(customer._links.address.href.replace('{addressTypeCode}', addressTypeCode))
            .then((payload) => {
                return payload.data;
            }).then((address) => {
                dispatch(getBillingAddressSuccess(address));
            }).catch((error) => {
                dispatch(getBillingAddressFailure(error.response));
            });
    }
}


export const updateAddress = (address, payload) => {
    return (dispatch) => {
        dispatch(updateBillingAddressStarted());
        return axios.post(  address._links.updateAddress.href,
                            payload)
        .then((payload) => {
            return payload.data;
        }).then((address) => {
            dispatch(updateBillingAddressSuccess(address));
        }).catch((error) => {
            dispatch(updateBillingAddressFailure(error.response));
        });
    }
}