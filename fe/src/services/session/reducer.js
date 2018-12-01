import * as actionTypes from './actionTypes';

export const initialState = {
  tokens: {
    access_token: null,
    token_type: null,
    refresh_token: null,
    expires_in: null,
    scope: null
  }
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
