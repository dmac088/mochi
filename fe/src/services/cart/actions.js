import * as actionTypes from './actionTypes';

export const update = cart => ({
	type: actionTypes.UPDATE_CART,
	cart,
});

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

export const removeItem = (cart, productId) => ({
	type: actionTypes.REMOVE_ITEM,
	cart,
	productId,
});
