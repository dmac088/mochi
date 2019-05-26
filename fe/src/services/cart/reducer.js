import * as actionTypes from './actionTypes';
import _ from 'lodash';

export const initialState = {
															items: [],
															totalItems: 0,
															totalAmount: 0,
														};

export const reducer = (state = initialState, action) => {
  let cartClone = _.cloneDeep(action.cart);

	switch (action.type) {
		case actionTypes.ADD_ITEM:
			cartClone.items.push(action.item);
			return cartClone;

		case actionTypes.UPDATE_ITEM:
			cartClone.items[action.index] = action.item;
			return cartClone;

		case actionTypes.REMOVE_ITEM:
			cartClone.items = cartClone.items.filter(function(value, index, arr){
															return value.productId !== action.productId;
												});
			return cartClone;
		case actionTypes.UPDATE_CART_TOTALS:
			cartClone.totalItems = action.totalItems;
			cartClone.totalAmount = action.totalAmount;
			return cartClone;

		case actionTypes.UPDATE_CART_ITEMS:
			const newItems = cartClone.items.map(o => {
				return Object.assign(o, 0, action.items.filter(i => i.productId === o.productId)[0]);
			});
			cartClone.items = newItems;
			return cartClone;

		default:
			return state;
	}
};
