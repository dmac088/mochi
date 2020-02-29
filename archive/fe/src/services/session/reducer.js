import * as actionTypes from './actionTypes';

export const initialState = {
                              tokens: {
                                access_token: null,
                                accessTokenExpiryDate: null,
                                token_type: null,
                                refresh_token: null,
                                expires_in: null,
                                scope: null,
                                authenticated: false
                              }
                            };

//the action object contains both the type and the session object
export const reducer = (state = initialState, action) => {

  //action contains type
	switch (action.type) {

    //is the action type our pre-defined action type specified in actionTypes.js
		case actionTypes.UPDATE:
			return {

        //action contians session
        //and we create a shallow copy of it by calling ...
        //we really should create a deep copy if our object structure has more than one level
				...action.session,
			};

    //if there is no action type specificed then return the same state object
    //that was passed
		default:
			return state;
	}
};
