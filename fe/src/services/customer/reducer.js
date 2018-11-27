import * as actionTypes from './actionTypes';

export const initialState =
const initialStateSignup = () => {
															"customer": {
																		"@class": ".Person",
																		"partyUser": {
																				"username": "",
																				"password": ""
																		},
																		"familyNameEn": "",
																		"nameCn": "",
																		"givenNameEn": ""
															}
												}
};;

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
