import * as actionTypes from './actionTypes';
import _ from 'lodash';

export const initialState = {
															cart: {
																	items: [],
																	totalItems: 0,
																	totalAmount: 0,
															}
														};

export const reducer = (state = initialState, action) => {
	//in this function we should accept the this.state
	//take a copy of the the state
	//then perform some updates on that state copy based on the action parameter
	//return the new state
	
	switch (action.type) {
		case actionTypes.UPDATE:
		console.log(action);
		return 	_.cloneDeep(action.cart);

		default:
			return state;
	}
};
