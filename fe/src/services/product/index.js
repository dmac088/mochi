
import store from '../../store';
import * as session from '../session';
import * as api from './api';
import * as actionCreators from './actions';
import * as productActionCreators from '../product/actions';
import { initialState } from './reducer';

	export const findAll = (locale) =>
		api.findAll(locale)
		.then((response) => {
      return response.text();
    })
    .then((responseText)=> {
      return JSON.parse(responseText);
    })
    .then((responseJSON)=> {
			//we dont want to commit the product list to redux
      //store.dispatch(productActionCreators.update({"items": responseJSON}));
			return responseJSON;
    })
		.then(onRequestSuccess)
		.catch(onRequestFailed);

	export const findByCategory = (locale, categoryId) =>
		api.findByCategory(locale, categoryId)
		.then((response) => {
			return response.text();
		})
		.then((responseText)=> {
			return JSON.parse(responseText);
		})
		.then((responseJSON)=> {
			//we dont want to commit the product list to redux
			//store.dispatch(productActionCreators.update({"items": responseJSON}));
			return responseJSON;
		})
		.then(onRequestSuccess)
		.catch(onRequestFailed);

	const onRequestSuccess = (response) => {
		console.log('request successfully completed!');
		return response;
	};

	const onRequestFailed = (exception) => {
		console.log('request failed!');
		session.clearSession();
		throw exception;
	};
