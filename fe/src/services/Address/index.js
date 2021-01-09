import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import {
    getAddressStarted,
    getAddressSuccess,
    getAddressFailure,
    updateAddressStarted,
    updateAddressSuccess,
    updateAddressFailure,
} from "../../actions/AddressActions";

export const getAddress = (customer, addressTypeCode) => {
    return (dispatch) => {
        dispatch(getAddressStarted());
        
        return axios.get(customer._links.address.href.replace('{addressTypeCode}', addressTypeCode))
            .then((payload) => {
                return payload.data;
            }).then((address) => {
                dispatch(getAddressSuccess(address));
            }).catch((error) => {
                dispatch(getAddressFailure(error.response));
            });
    }
}


export const updateAddress = (address, payload) => {
    return (dispatch) => {
        dispatch(updateAddressStarted());
        console.log(address);
        return axios.post(  address._links.updateAddress.href,
                            payload)
        .then((payload) => {
            return payload.data;
        }).then((address) => {
            dispatch(updateAddressSuccess(address));
        }).catch((error) => {
            dispatch(updateAddressFailure(error.response));
        });
    }
}