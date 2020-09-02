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

const checkProduct = (items, productCode) => {
    return items.some(function (item) {
        return item.productCode === productCode;
    });
}

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
    return (dispatch) => {
        console.log(item);
    }
}

/*
export const addItem = (item) => {
    return (dispatch) => {
        const allItems = localStorageService.getItems("bagItems") || [];

        if (checkProduct(allItems, item.productCode)) {
            const foundItem = allItems.find(x => x.productCode === item.productCode);
            const updatedItem = {
                ...foundItem,
                quantity: Number(foundItem.quantity) + Number(item.quantity),
            }
            const newAllItems = allItems.filter(i => i.productCode !== item.productCode);
            newAllItems.push(updatedItem)
            localStorageService.setItems(JSON.stringify(newAllItems));
            dispatch(getItems());
            return;
        }
        allItems.push(item);
        localStorageService.setItems(JSON.stringify(allItems));
        dispatch(getItems());
    }
}
*/

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


