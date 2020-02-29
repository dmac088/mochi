import * as actionTypes from './actionTypes';
//we get the individual customer record from https://localhost:8090/api/Party/UserName/{UserName}

const initialState = {
		categories: null
};

export const reducer = (state = initialState, action) => {
	switch (action.type) {
		case actionTypes.UPDATE:
			return {
				categories: {
					...state.categories,
					...action.categories.reduce((prev, curr) => ({
						...prev,
						[curr.id]: curr,
					}), {}),
				},
			};
		case actionTypes.EMPTY:
			//empty the object i.e. when we clear the session
			return {
				categories: {},
			};
		default:
			return state;
	}
};
