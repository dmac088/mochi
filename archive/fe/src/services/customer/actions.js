import * as actionTypes from './actionTypes';

export const update = customer => ({
	type: actionTypes.UPDATE,
	customer,
});
