import * as session from '../session';
import * as api from './api';

	export const findAll = (locale, category, term, page, size, sort) => {
		let termT = ((!term) ? "-Z" : term);
		return api.findAll(locale, category, term, page, size, sort)
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
		.catch(onRequestFailed)
	};

	const onRequestSuccess = (response) => {
		console.log('request successfully completed!');
		return response;
	};

	const onRequestFailed = (exception) => {
		console.log('request failed!');
		session.clearSession();
		throw exception;
	};
