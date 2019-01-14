import { AsyncStorage } from 'react';
import { createStore, combineReducers, compose, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import devTools from 'remote-redux-devtools';
import { persistStore, autoRehydrate } from 'redux-persist';
import createFilter from 'redux-persist-transform-filter';

//these are the output from combineReducers so the redux store is aware of the reducers
import { reducer as dataReducer } from './data/reducer';
import { reducer as servicesReducer } from './services/reducer';

import * as persistActionCreators from './services/persist/actions';


//again combineReducers
const appReducer = combineReducers({
	services: servicesReducer,
	data: dataReducer,
});

const enhancer = compose(
	applyMiddleware(
		thunk,
	),
	devTools()
);

const store = createStore(
	//pass out reducers
 	appReducer,
	//pass our middleware
 	enhancer,
	//no idea waht this does yet
	autoRehydrate(),
);

const saveAndLoadSessionFilter = createFilter(
	//we want to persist data from services reducers
  'services',
  // ['session'],
  // ['session']
);

export const persist = persistStore(store, {
	storage: AsyncStorage,
	blacklist: ['data'],
	transforms: [saveAndLoadSessionFilter],
}, () => store.dispatch(persistActionCreators.update({ isHydrated: true })));

export default store;
