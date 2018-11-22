/* global fetch */

import fetchival from 'fetchival';
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



export const fetchApi = (endPoint, payload = {}, formData = {}, method = 'get', headers = {}) => {


	let formBody = [];
		for (let property in formData) {
				let encodedKey = encodeURIComponent(property);
				let encodedValue = encodeURIComponent(formData[property]);
				formBody.push(encodedKey + "=" + encodedValue);
		}
	formBody = formBody.join("&");

	return fetch(apiConfig.url+endPoint, {
		crossDomain: true,
		method: method,
	  headers: headers,
	  body: formBody

		//we only catch network errors here
	}).catch((err) => {
		console.log(err);
	});
}
