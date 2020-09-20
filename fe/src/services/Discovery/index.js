import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import { discover } from '../../actions/DiscoveryActions';
import { getAllCategories } from '../Category/index';
import { getBag, getBagContents } from '../Bag/index';

export const discoverAll = () =>
    axios.get(`https://localhost:8090/api/Discovery`);

export const initialize = () => {
    return (dispatch, getState) => {
        return dispatch(discover())
            .then(() => {
                //there is no dependency between getAllCategories and getBag
                dispatch(getAllCategories());
                dispatch(getBag())
                .then(() => {
                    dispatch(getBagContents());
                });
            });
    }
}