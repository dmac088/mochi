import * as actionTypes from './actionTypes';

export const initialState = {
															customer: {
																					userName: null,
																					password: null,
																					enabled: null,
																					firstName: null,
																					lastName: null
																				}
														};

export const reducer = (state = initialState, action) => {
	console.log('customer reducer was fired!');
	switch (action.type) {
		case actionTypes.UPDATE:
			return {
				...action.customer,
			};
		default:
			return state;
	}
};
