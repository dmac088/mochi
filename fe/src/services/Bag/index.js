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
    addBagItemStarted,
    addBagItemSuccess,
    addBagItemFailure,
    removeBagItemStarted,
    removeBagItemSuccess,
    removeBagItemFailure,
} from '../../actions/BagContentsActions';

const localStorageService = LocalStorageService.getService();

export const add = (item) => {
    return (dispatch) => {
        return dispatch(addItem(item));
    }
}

export const addItem = (item) => {
    return (dispatch, getState) => {

        dispatch(addBagItemStarted()); 

        return axios.post(getState().bag.links.addItem.href,item)
                    .then(() => {
                        dispatch(addBagItemSuccess());
                        dispatch(getBagAndItems());
                    })
                    .catch(() => {
                        dispatch(addBagItemFailure()); 
                    });
    }
}

export const removeItem = (itemCode) => {
    return (dispatch, getState) => {
        return axios.post(getState().bag.links.removeItem.href,itemCode)
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



