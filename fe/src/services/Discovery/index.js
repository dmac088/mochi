import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import { discover } from '../../actions/DiscoveryActions';
import { getAllCategories } from '../Category/index';
import { getBag, getBagItems } from '../Bag/index';

export const discoverAll = () =>
    axios.get(`https://localhost:8090/api/Discovery`);

export const initialize = () => {
    return (dispatch, getState) => {
        return dispatch(discover())
            .then(() => {
                dispatch(getAllCategories())
                    .then(() => {
                        dispatch(getBag())
                            .then(() => {
                                dispatch(getBagItems(getState().bag));
                            })
                    });
            });
    }
}