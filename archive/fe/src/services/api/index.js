/* global fetch */

import _ from 'lodash';
import apiConfig from './config';
import { refreshToken } from '../session';
import * as sessionSelectors from '../session/selectors';


export const exceptionExtractError = (exception) => {
	if (!exception.Errors) return false;
	const errorKeys = Object.keys(exception.Errors);
	if (errorKeys.length > 0) {
		return exception.Errors[errorKeys[0]][0].message;
	}
	return false;
};

export const getParams = (method, headers) => {
	const { access_token, refresh_token } = sessionSelectors.get().tokens;
	console.log("access_token = " + access_token);
	console.log("refresh_token = " + refresh_token);
	return {
					method: method,
					headers:  _.pickBy({
																...(access_token ? {
																			Authorization: `Bearer ${access_token}`,
																} : {}),
																			...headers,
														 }, item => !_.isEmpty(item)),
	 }
}

export const fetchApi = (endPoint, payload = {}, formData = {}, method = 'get', headers = {}) => {

	let formBody = [];
		for (const property in formData) {
				const encodedKey = encodeURIComponent(property);
				const encodedValue = encodeURIComponent(formData[property]);
				formBody.push(encodedKey + "=" + encodedValue);
		}

	(method.toLowerCase() === 'post') ? formBody.push(JSON.stringify(payload)) : formBody.push(null);
	formBody = formBody.join("&");
	let params = getParams(method, headers);
	Object.assign(params, (method.toLowerCase() === 'post') && { body: formBody })
	console.log(apiConfig.url+endPoint);
	return fetch(apiConfig.url+endPoint, params)
				.then((response) => {
					if(response.status === 401) {
						console.log("Error: " + response.status);
						return refreshToken()
						.then(() => {
							return fetchApi(endPoint, payload, formData, method, headers);
						});
					}
					return response;
				});
}
