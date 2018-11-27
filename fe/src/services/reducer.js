import { combineReducers } from 'redux';
import { reducer as routeHistoryReducer } from './routeHistory/reducer';
import { reducer as sessionReducer } from './session/reducer';
import { reducer as persistReducer } from './persist/reducer';
import { reducer as customerReducer } from './customer/reducer';

export const reducer = combineReducers({
	routeHistory: routeHistoryReducer,
	session: sessionReducer,
	persist: persistReducer,
	customer: customerReducer,
});
