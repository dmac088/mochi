import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import LocalStorageService from "../../components/Layout/Helpers/Storage/Bag/LocalStorageService";
import {
    getBagStarted,
    getBagSuccess,
    getBagFailure,
    getBagItemsStarted,
    getBagItemsSuccess,
    getBagItemsFailure,
    removeBagItemStarted,
    removeBagItemSuccess,
} from '../../actions/BagActions';

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
export const getBagItems = () => {
    return (dispatch, getState) => {

        dispatch(getBagItemsStarted());  

        return axios.get(getState().bag.links.bagContents.href)
        .then((payload) => {
            return payload.data._embedded.bagItemResources;
        }).then((items) => {
            dispatch(getBagItemsSuccess(items || []));
        }).catch((error) => {
            dispatch(getBagItemsFailure(error.response));
        });
    }
}

export const getBagAndItems = () => {
    return (dispatch) => {
        dispatch(getBag())
        .then((response) => {
            console.log(response);
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


