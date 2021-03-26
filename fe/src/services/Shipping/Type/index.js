import { instance as axios } from "../../../components/Layout/Helpers/api/axios";
import {
    getShippingTypeStarted,
    getShippingTypeSuccess,
    getShippingTypeFailure,
} from "../../../actions/ShippingTypeActions";

export const getShippingType = (destination, weight) => {
    
     return (dispatch) => {
         dispatch(getShippingTypeStarted());

        return axios.get(destination._links.shippingTypes.href.replace('{bagWeight}', weight))
        .then((payload) => {
            return payload.data;
        }).then((Types) => {
            dispatch(getShippingTypeSuccess(Types));
        }).catch((error) => {
            dispatch(getShippingTypeFailure(error.response));
        });
     }
}
