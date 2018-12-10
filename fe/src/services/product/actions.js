import * as actionTypes from './actionTypes';

export const update = items => ({
	type: actionTypes.UPDATE,
	items,
});
