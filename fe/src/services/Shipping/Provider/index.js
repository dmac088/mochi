import { instance as axios } from "../../../components/Layout/Helpers/api/axios";
import {
    getShippingProviderStarted,
    getShippingProviderSuccess,
    getShippingProviderFailure,
} from "../../../actions/ShippingProviderActions";


export const getShippingProviders = (discovery) => {
    return (dispatch) => {
        dispatch(getShippingProviderStarted());
        
        return axios.get(discovery.links.getShippingProviders.href)
        .then((payload) => {
            return payload.data;
        }).then((providers) => {
            dispatch(getShippingProviderSuccess(providers));
        }).catch((error) => {
            dispatch(getShippingProviderFailure(error.response));
        });
    }
}
