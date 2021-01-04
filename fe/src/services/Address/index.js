import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import {
    getAddressStarted,
    getAddressSuccess,
    getAddressFailure,
    updateAddressStarted,
    updateAddressSuccess,
    updateAddressFailure,
} from "../../actions/AddressActions";

export const getAddress = (customer) => {
    return (dispatch) => {
        dispatch(getAddressStarted());
        return axios.get(customer._links.address.href)
            .then((payload) => {
                return payload.data;
            }).then((address) => {
                dispatch(getAddressSuccess(address));
            }).catch((error) => {
                dispatch(getAddressFailure(error.response));
            });
    }
}


export const updateAddress = () => {

    dispatch(updateAddressStarted());

}