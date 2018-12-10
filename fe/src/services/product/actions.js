import * as actionTypes from './actionTypes';

export const update = products => ({
	type: actionTypes.UPDATE,
	products,
});
