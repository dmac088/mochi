import * as actionTypes from './actionTypes';

export const initialState = {
	tokens: {
		access: {
			type: null,
			value: null,
			expiresIn: null,
		},
		refresh: {
			type: null,
			value: null,
		},
	},
	user: {
		id: null,
		authenticated: false
	},
};

export const reducer = (state = initialState, action) => {
	console.log('session reducer was fired!');
	switch (action.type) {
		case actionTypes.UPDATE:
			return {
				...action.session,
			};
		default:
			return state;
	}
};
