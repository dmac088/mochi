import { combineReducers } from 'redux';
import { reducer as usersReducer } from './users/reducer';
import { reducer as customerReducer } from './customers/reducer';

export const reducer = combineReducers({
	users: usersReducer,
	customer: customerReducer,
});
