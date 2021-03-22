import { instance as axios } from "../../../components/Layout/Helpers/api/axios";
import {
    getShippingTypeStarted,
    getShippingTypeSuccess,
    getShippingTypeFailure,
} from "../../../actions/ShippingTypeActions";

export const getShippingType = (discovery) => {
    return (dispatch) => {
        dispatch(getShippingTypeStarted());
        
        return axios.get(discovery.links.getShippingTypes.href)
        .then((payload) => {
            return payload.data;
        }).then((Types) => {
            dispatch(getShippingTypeSuccess(Types));
        }).catch((error) => {
            dispatch(getShippingTypeFailure(error.response));
        });
    }
}
