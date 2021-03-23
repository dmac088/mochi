import { instance as axios } from "../../../components/Layout/Helpers/api/axios";
import {
    getShippingTypeStarted,
    getShippingTypeSuccess,
    getShippingTypeFailure,
} from "../../../actions/ShippingTypeActions";

export const getShippingType = (destination) => {
    
     return (dispatch) => {
     //   console.log(destination);
    //     dispatch(getShippingTypeStarted());
        
    //     return axios.get(discovery.links.getShippingTypes.href)
    //     .then((payload) => {
    //         return payload.data;
    //     }).then((Types) => {
    //         dispatch(getShippingTypeSuccess(Types));
    //     }).catch((error) => {
    //         dispatch(getShippingTypeFailure(error.response));
    //     });
     }
}
