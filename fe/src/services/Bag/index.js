import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import LocalStorageService from "../../components/Layout/Helpers/Storage/Bag/LocalStorageService";
import {
    getBagItemsStarted,
    getBagItemsSuccess,
    getBagItemsFailure
} from '../../actions/BagActions';
import store from '../../store';

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

    const bagItems = localStorageService.getItems();

    store.dispatch(getBagItemsStarted());
    const state = store.getState();

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
            store.dispatch(getBagItemsSuccess(items));
        }).catch((error) => {
            store.dispatch(getBagItemsFailure(error.response));
        });
}


export const removeItem = (productCode) => {
    const allItems = localStorageService.getItems("bagItems");
    const newAllItems = allItems.filter(i => i.productCode !== productCode);
    localStorageService.setItems(JSON.stringify(newAllItems));
    getItems(localStorageService.getItems());
}

