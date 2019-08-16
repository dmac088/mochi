import * as actionTypes from './actionTypes';

export const addItem = (cart, item) => ({
	type: actionTypes.ADD_ITEM,
	cart,
	item,
});

export const updateItem = (cart, index, item) => ({
	type: actionTypes.UPDATE_ITEM,
	cart,
	index,
	item,
});

export const removeItem = (cart, productUPC) => ({
	type: actionTypes.REMOVE_ITEM,
	cart,
	productUPC,
});

export const updateCartTotals = (cart, totalItems, totalAmount) => ({
	type: actionTypes.UPDATE_CART_TOTALS,
	cart,
	totalItems,
	totalAmount,
});

export const updateCartItems = (cart, items) => ({
	type: actionTypes.UPDATE_CART_ITEMS,
	cart,
	items,
});
