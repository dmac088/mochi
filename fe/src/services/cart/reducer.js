import * as actionTypes from './actionTypes';
import _ from 'lodash';

export const initialState = {
															items: [],
															totalItems: 0,
															totalAmount: 0,
														};

export const reducer = (state = initialState, action) => {
	//in this function we should accept the this.state
	//take a copy of the the state
	//then perform some updates on that state copy based on the action parameter
	//return the new state


	//currently what I'm doing is craeting a copy of the object outside of the reducer
	//modifying the object (update)
	//then passing that new object to the reducer
	//the reducer then simply takes a copy of the object and passing it back to me
  let cartClone = null;
	switch (action.type) {
		case actionTypes.ADD_ITEM:
			cartClone = _.cloneDeep(action.cart);
			cartClone.items.push(action.item);
			return cartClone;
		case actionTypes.UPDATE_ITEM:
			cartClone = _.cloneDeep(action.cart);
			cartClone.items[action.index] = action.item;
			return cartClone;
		case actionTypes.REMOVE_ITEM:
			cartClone = _.cloneDeep(action.cart);
			cartClone.items = cartClone.items.filter(function(value, index, arr){
															return value.productDTO.productId !== action.productId;
												});
			return cartClone;
		case actionTypes.UPDATE_CART_TOTALS:
			cartClone = _.cloneDeep(action.cart);
			cartClone.totalItems = action.totalItems;
			cartClone.totalAmount = action.totalAmount;
		default:
			return state;
	}
};
