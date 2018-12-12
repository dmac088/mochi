import * as actionTypes from './actionTypes';

export const initialState = {
														cart: {
																items: []
															}
														};

export const reducer = (state = initialState, action) => {
	switch (action.type) {
		case actionTypes.UPDATE:
			return {
				...action.cart,
			};
		default:
			return state;
	}
};
