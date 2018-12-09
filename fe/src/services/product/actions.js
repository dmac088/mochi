import * as actionTypes from './actionTypes';

export const update = product => ({
	type: actionTypes.UPDATE,
	product,
});
