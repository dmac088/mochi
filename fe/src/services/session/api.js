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
																									Authorization: `Basic ${new Buffer('spring-security-oauth2-read-write-client:spring-security-oauth2-read-write-client-password1234').toString('base64')}`,
																									'Content-Type': 'application/x-www-form-urlencoded',
																									'Cache-Control': 'no-cache'
																								});

export const refresh = (token, user) => fetchApi(endPoints.refresh, { token, user }, 'post', {
	'Client-ID': apiConfig.clientId,
	Authorization: null,
});

export const revoke = tokens => fetchApi(endPoints.revoke, { tokens }, 'post');
