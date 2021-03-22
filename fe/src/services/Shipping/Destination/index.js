import { instance as axios } from "../../../components/Layout/Helpers/api/axios";
import {
    getShippingDestinationsStarted,
    getShippingDestinationsSuccess,
    getShippingDestinationsFailure,
} from "../../../actions/ShippingDesinationActions";

export const getShippingDestinations = (discovery) => {
    return (dispatch) => {
        dispatch(getShippingDestinationsStarted());
        
        return axios.get(discovery.links.getShippingDestinations.href)
        .then((payload) => {
            return payload.data;
        }).then((providers) => {
            dispatch(getShippingDestinationsSuccess(providers));
        }).catch((error) => {
            dispatch(getShippingDestinationsFailure(error.response));
        });
    }
}
