/* global fetch */

import _ from 'lodash';
import apiConfig from './config';
import { refreshToken } from '../session';
import * as sessionSelectors from '../session/selectors';


export const exceptionExtractError = (exception) => {
	if (!exception.Errors) return false;
	let error = false;
	const errorKeys = Object.keys(exception.Errors);
	if (errorKeys.length > 0) {
		error = exception.Errors[errorKeys[0]][0].message;
	}
	return error;
};


export const getParams = (method, headers, item) => {
	return {
		method: method,
		headers:  _.pickBy({
													...(sessionSelectors.get().tokens.access_token ? {
													Authorization: `Bearer ${sessionSelectors.get().tokens.access_token}`,
												 } : {}),
													...headers,
											 }, item => !_.isEmpty(item)),
	 };
}

export const fetchApi = (endPoint, payload = {}, formData = {}, method = 'get', headers = {}) => {
	const accessToken = sessionSelectors.get().tokens.access_token;
	let formBody = [];
		for (const property in formData) {
				const encodedKey = encodeURIComponent(property);
				const encodedValue = encodeURIComponent(formData[property]);
				formBody.push(encodedKey + "=" + encodedValue);
		}

	(method.toLowerCase() === 'post') ? formBody.push(JSON.stringify(payload)) : formBody.push(null);

	formBody = formBody.join("&");

	Object.assign(getParams(method, headers, null), (method.toLowerCase() === 'post') && { body: formBody });

	console.log(apiConfig.url+endPoint);
	return fetch(apiConfig.url+endPoint, getParams(method, headers, null))
				.then((response) => {
					if(response.status === 401) {
						return refreshToken()
						.then(() => {
							return fetch(apiConfig.url+endPoint, getParams(method, headers, null));
						});
					}
					return response;
				});
}
