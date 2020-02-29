import * as actionTypes from './actionTypes';
//we get the individual customer record from https://localhost:8090/api/Party/UserName/{UserName}

const initialState = {
		customers: null
};

export const reducer = (state = initialState, action) => {
	switch (action.type) {
		case actionTypes.UPDATE:
			return {
				customers: {
					...state.customers,
					...action.customers.reduce((prev, curr) => ({
						...prev,
						[curr.id]: curr,
					}), {}),
				},
			};
		case actionTypes.EMPTY:
			//empty the object i.e. when we clear the session
			return {
				customers: {},
			};
		default:
			return state;
	}
};
