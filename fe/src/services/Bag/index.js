import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import LocalStorageService from "../../components/Layout/Helpers/Storage/Bag/LocalStorageService";
import {
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
                dispatch(getItems());
            })
            .catch((error) => {
                console.log(error);
            })
    }
}

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


export const getItems = () => {
    return (dispatch, getState) => {

        const state = getState();
        const bagItems = localStorageService.getItems();
        dispatch(getBagItemsStarted());

        axios.post(state.discovery.links.getProducts.href, bagItems.map(i => i.productCode))
            .then((payload) => {
                return payload.data._embedded.productResources;
            }).then((products) => {
                const items = products.map(p => {
                    return {
                        ...p,
                        'quantity': bagItems.filter(i => i.productCode === p.data.productUPC)[0].quantity,
                    }
                });
                dispatch(getBagItemsSuccess(items));
            }).catch((error) => {
                dispatch(getBagItemsFailure(error.response));
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

export const sumTotalItems = (items) => {
    let total = 0;
    total = items.length;
    return total;
}

export const sumTotalAmount = (items) => {
    let total = 0;
    for (var i = 0; i < items.length; i++) {
        total += items[i].data.productMarkdown * parseInt(items[i].quantity);
    }
    return total;
}

