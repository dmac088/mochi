/* global fetch */

import _ from 'lodash';
import apiConfig from './config';
import * as sessionSelectors from '../session/selectors'


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
	const accessToken = sessionSelectors.get().tokens.access_token;
	console.log("access token = " + accessToken);
	let formBody = [];
		for (const property in formData) {
				const encodedKey = encodeURIComponent(property);
				const encodedValue = encodeURIComponent(formData[property]);
				formBody.push(encodedKey + "=" + encodedValue);
		}

	(method.toLowerCase() === 'post') ? formBody.push(JSON.stringify(payload)) : formBody.push(null);

	formBody = formBody.join("&");

	let params = {
		method: method,
	  headers:  _.pickBy({
														...(accessToken ? {
															Authorization: `Bearer ${accessToken}`,
														} : {}),
														...headers,
													}, item => !_.isEmpty(item)),
	 };

	Object.assign(params, (method.toLowerCase() === 'post') && { body: formBody })
	console.log(apiConfig.url+endPoint);
	return fetch(apiConfig.url+endPoint, params);
}
