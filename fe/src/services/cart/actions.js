import * as actionTypes from './actionTypes';

export const update = cart => ({
	type: actionTypes.UPDATE_CART,
	cart,
});

export const addProduct = (cart, item) => ({
	type: actionTypes.ADD_PRODUCT,
	cart,
	item,
});

export const updateProduct = (cart, index, item) => ({
	type: actionTypes.UPDATE_PRODUCT,
	cart,
	index,
	item,
});

export const updateCartItems = (cart, items) => ({
	type: actionTypes.UPDATE_CART_ITEMS,
	cart,
	items,
});
