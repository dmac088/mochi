import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import LocalStorageService from "../../components/Layout/Helpers/Storage/Bag/LocalStorageService";
import {
    getBagItemsStarted,
    getBagItemsSuccess,
    getBagItemsFailure,
    removeBagItemStarted,
    removeBagItemSuccess,
    removeBagItemFailure,
} from '../../actions/BagActions';

const localStorageService = LocalStorageService.getService();

const checkProduct = (items, productCode) => {
    return items.some(function (item) {
        return item.productCode === productCode;
    });
}

export function addItem(item) {

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
        getItems(localStorageService.getItems());
        return;
    }

    allItems.push(item);
    localStorageService.setItems(JSON.stringify(allItems));
    getItems(localStorageService.getItems());
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
        console.log('product code = ' + productCode);
        console.log(newAllItems);
        localStorageService.setItems(JSON.stringify(newAllItems));
        dispatch(removeBagItemSuccess(productCode));
    }
}

