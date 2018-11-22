import { fetchApi } from '../../services/api';
import apiConfig from '../../services/api/config';

const endPoints = {
	authenticate: '/oauth/token',
	revoke: '/oauth/token',
	refresh: '/oauth/token',
};

export const authenticate = (email, password) => fetchApi(
																													endPoints.authenticate,
																													{},
																													{
																														username: email,
																														password: password,
																														grant_type: 'password'
																													},
																													'POST',
																													{
																															Authorization: 'Basic ' + apiConfig.clientId,
																															'Content-Type': 'application/x-www-form-urlencoded',
																															'Cache-Control': 'no-cache'
																													}
																								);

export const refresh = (token) 									=> fetchApi(
																													endPoints.refresh,
																													{},
																													{
																														grant_type: 'refresh_token',
																														refresh_token: token.value
																													},
																													'POST',
																													{
																														Authorization: 'Basic ' + apiConfig.clientId,
																														'Content-Type': 'application/x-www-form-urlencoded',
																														'Cache-Control': 'no-cache'
																													}
																									);

export const revoke = tokens 									=> fetchApi(
																													endPoints.revoke,
																													{},
																													{ tokens },
																													'post'
																									);
