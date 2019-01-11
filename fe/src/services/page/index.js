import * as session from '../session';
import * as api from './api';
import { initialState } from './reducer';

	export const findAll = (locale = "ENG", categoryId = 2, page = 0, size = 10) =>
		api.findAll(locale, categoryId, page, size)
		.then((response) => {
			console.log(categoryId);
			console.log(response);
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
