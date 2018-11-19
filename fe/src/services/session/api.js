import { Buffer } from 'buffer';
import { fetchApi } from '../../services/api';
import apiConfig from '../../services/api/config';

const endPoints = {
	authenticate: '/oauth/token',
	revoke: '/oauth/token',
	refresh: '/oauth/token',
};

export const authenticate = (email, password) => fetchApi(
																								endPoints.authenticate,
																								{
																										username: email,
																										password: password,
																										grant_type: 'password'
																								},
																								'post',
																								{
																									Authorization: 'Basic ' + apiConfig.clientId,
																									'Content-Type': 'application/x-www-form-urlencoded',
																									'Cache-Control': 'no-cache'
																								});

export const refresh = (token, user) => fetchApi(endPoints.refresh, { token, user }, 'post', {
	'Client-ID': apiConfig.clientId,
	Authorization: null,
});

export const revoke = tokens => fetchApi(endPoints.revoke, { tokens }, 'post');
