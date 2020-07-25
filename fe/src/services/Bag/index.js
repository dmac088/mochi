import LocalStorageService from "../../components/Layout/Helpers/Storage/Bag/LocalStorageService";
import { getBagItems } from "../../actions/BagActions";
import store from '../../store';

const localStorageService = LocalStorageService.getService();

const checkProduct = (items, productCode) => {
    return items.some(function (item) {
        return item.productCode === productCode;
    });
}

export function addItem(item) {

    const allItems = localStorageService.getItems("allItems") || [];

    if (checkProduct(allItems, item.productCode)) {
        const foundItem = allItems.find(x => x.productCode === item.productCode);
        const updatedItem = {
            ...foundItem,
            quantity: Number(foundItem.quantity) + Number(item.quantity),
        }
        const newAllItems = allItems.filter(i => i.productCode !== item.productCode);
        newAllItems.push(updatedItem)
        localStorageService.setItems(JSON.stringify(newAllItems));
        store.dispatch(getBagItems(localStorageService.getItems()));
        return;
    }

    allItems.push(item);
    localStorageService.setItems(JSON.stringify(allItems));
    store.dispatch(getBagItems(localStorageService.getItems()));
}


export const getItems = () => {
    store.dispatch(getBagItems(localStorageService.getItems()));
}


export function removeItem(item) {

    const allItems = localStorageService.getItems("allItems");

    // if (checkProduct(allItems, item.productCode)) {
    //     const foundItem = allItems.find(x => x.productCode === item.productCode);
    //     const updatedItem = {
    //         ...foundItem,
    //         quantity: Number(foundItem.quantity) + Number(item.quantity),
    //     }
    //     const newAllItems = allItems.filter(i => i.productCode !== item.productCode);
    //     newAllItems.push(updatedItem)
    //     localStorageService.setItems(JSON.stringify(newAllItems));
    //     store.dispatch(getBagItems());
    //     return;
    // }
    // allItems.push(item);
    // localStorageService.setItems(JSON.stringify(allItems));
    // store.dispatch(getBagItems());
}
