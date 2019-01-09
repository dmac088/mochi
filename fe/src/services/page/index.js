
import store from '../../store';
import * as session from '../session';
import * as api from './api';
import * as actionCreators from './actions';
import * as productActionCreators from '../page/actions';
import { initialState } from './reducer';

	export const findAll = (locale, page) =>
		api.findAll(locale, page)
		.then((response) => {
      return response.text();
    })
    .then((responseText)=> {
      return JSON.parse(responseText);
    })
    .then((responseJSON)=> {
			return responseJSON;
    })
		.then(onRequestSuccess)
		.catch(onRequestFailed);

	export const findByCategory = (locale, categoryId, page) =>
		api.findByCategory(locale, categoryId, page)
		.then((response) => {
			return response.text();
		})
		.then((responseText)=> {
			return JSON.parse(responseText);
		})
		.then((responseJSON)=> {
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
