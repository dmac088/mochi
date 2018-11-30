/* global fetch */

import _ from 'lodash';
import * as sessionSelectors from '../../services/session/selectors';
import apiConfig from './config';


export const exceptionExtractError = (exception) => {
	if (!exception.Errors) return false;
	let error = false;
	const errorKeys = Object.keys(exception.Errors);
	if (errorKeys.length > 0) {
		error = exception.Errors[errorKeys[0]][0].message;
	}
	return error;
};

export const deepValue = (obj, path, value)  => {
				var parts = path.split('.');
				var curr = obj;
				for(var i=0;i<parts.length-1;i++)
						curr = curr[parts[i]] || {};
				curr[parts[parts.length-1]] = value;
}

export const fetchApi = (endPoint, payload = {}, formData = {}, method = 'get', headers = {}) => {
	console.log('fetch api called: ' + apiConfig.url+endPoint);
	console.log('payload.....');
	console.log(payload);

	console.log('formData.... ');
	console.log(formData);

	console.log('headers = ');
	console.log(headers);
	let formBody = [];
		for (let property in formData) {
				let encodedKey = encodeURIComponent(property);
				let encodedValue = encodeURIComponent(formData[property]);
				formBody.push(encodedKey + "=" + encodedValue);
		}

	if(method.toLowerCase() === 'post') {
		formBody.push(JSON.stringify(payload));
	}
	formBody = formBody.join("&");

	return fetch(apiConfig.url+endPoint, {
		crossDomain: true,
		method: method,
	  headers: headers,
	  body: formBody
	});
}
