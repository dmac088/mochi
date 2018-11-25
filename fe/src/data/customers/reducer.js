import * as actionTypes from './actionTypes';
//we get the individual customer record from https://localhost:8090/api/Party/UserName/{UserName}

const initialState = {};

export const reducer = (state = initialState, action) => {
	switch (action.type) {
		case actionTypes.UPDATE:
			return {
				customer: {
					...state.customer,
					...action.customer.reduce((prev, curr) => ({
						...prev,
						[curr.id]: curr,
					}), {}),
				},
			};
		case actionTypes.EMPTY:
			//empty the object i.e. when we clear the session
			return {
				customer: {},
			};
		default:
			return state;
	}
};
