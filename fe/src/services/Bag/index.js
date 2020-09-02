import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import LocalStorageService from "../../components/Layout/Helpers/Storage/Bag/LocalStorageService";
import {
    getBagStarted,
    getBagSuccess,
    getBagFailure,
} from '../../actions/BagActions';

import {
    getBagContentsStarted,
    getBagContentsSuccess,
    getBagContentsFailure,
    removeBagItemStarted,
    removeBagItemSuccess,
} from '../../actions/BagContentsActions';

const localStorageService = LocalStorageService.getService();

export const add = (item) => {
    return (dispatch) => {
        return dispatch(addItem(item))
            .then(() => {
              //  dispatch(getItems());
            })
            .catch((error) => {
                console.log(error);
            })
    }
}

export const addItem = (item) => {
    return (dispatch, getState) => {
        return axios.post(getState().bag.links.addItem.href,
                          item)
                    .then((payload) => {
                        dispatch(getBagAndItems());
                    });
    }
}

export const getBagAndItems = () => {
    return (dispatch, getState) => {
        dispatch(getBag())
                    .then(() => {
                        dispatch(getBagContents(getState().bag));
                    });
    }
}
    

export const getBag = () => {
    return (dispatch, getState) => {
        const state = getState();
        
        dispatch(getBagStarted());  

        return axios.get(state.discovery.links.getBag.href)
        .then((payload) => {
             return payload.data;
        }).then((bag) => {
             dispatch(getBagSuccess(bag));
        }).catch((error) => {
             dispatch(getBagFailure(error.response));
        });
    }
}

//we need to inject the dependencies into the function
export const getBagContents = () => {
    return (dispatch, getState) => {

        dispatch(getBagContentsStarted());  

        return axios.get(getState().bag.links.bagContents.href)
        .then((payload) => {
            return payload.data._embedded.bagItemResources;
        }).then((items) => {
            dispatch(getBagContentsSuccess(items || []));
        }).catch((error) => {
            dispatch(getBagContentsFailure(error.response));
        });
    }
}

export const removeItem = (productCode) => {
    return dispatch => {
        dispatch(removeBagItemStarted());
        const allItems = localStorageService.getItems();
        const newAllItems = allItems.filter(i => i.productCode !== productCode);
        localStorageService.setItems(JSON.stringify(newAllItems));
        dispatch(removeBagItemSuccess(productCode));
    }
}


