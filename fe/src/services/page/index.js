import * as session from '../session';
import * as api from './api';
import { initialState } from './reducer';

	export const findAll = (locale, page, size) =>
		api.findAll(locale, page, size)
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

	export const findByCategory = (locale, categoryId, page, size) =>

		api.findByCategory(locale, categoryId, page, size)
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
