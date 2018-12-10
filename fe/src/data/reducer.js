import { combineReducers } from 'redux';
import { reducer as usersReducer } from './users/reducer';
import { reducer as customersReducer } from './customers/reducer';
import { reducer as productssReducer } from './products/reducer';

export const reducer = combineReducers({
	users: usersReducer,
	customers: customersReducer,
	products: productsReducer,
});
