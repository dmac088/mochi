import store from 'MobileApp/src/store';

//We have no business getting all customers for a logged in user, unlelss they happen to be an admin
// export const getAll = () => {
// 	const { items } = store.getState().data.customers;
// 	const itemsArray = Object.keys(items).map(itemKey => items[itemKey]);
// 	itemsArray.sort((item1, item2) => item1.id > item2.id);
// 	return itemsArray;
// };

export const get = () => {
	return store.getState().data.categories;
};
