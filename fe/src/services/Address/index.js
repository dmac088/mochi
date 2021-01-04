import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import {
    getAddressStarted,
    getAddressSuccess,
    getAddressFailure
} from "../../actions/AddressActions";

export const getAddress = () => {
    return (dispatch, getState) => {
        dispatch(getAddressStarted());
        const customer = getState().customer;
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