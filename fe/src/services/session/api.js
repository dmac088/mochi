import { fetchApi } from '../../services/api';
import apiConfig from '../../services/api/config';

const endPoints = {
	authenticate: '/oauth/token',
	revoke: '/oauth/token',
	refresh: '/oauth/token',
};

export const authenticate = (userName, password) => fetchApi(
																													endPoints.authenticate,
																													null,
																													{
																														username: userName,
																														password: password,
																														grant_type: 'password'
																													},
																													'POST',
																													{
																														 Authorization: 'Basic ' + apiConfig.ClientId,
																														'Content-Type': apiConfig.ContentType,
																														'Cache-Control': apiConfig.CacheControl
																													}
																								);

export const refresh = (token) 									=> fetchApi(
																													endPoints.refresh,
																													null,
																													{
																														grant_type: 'refresh_token',
																														refresh_token: token.value
																													},
																													'POST',
																													{
																														Authorization: 'Basic ' + apiConfig.ClientId,
																														'Content-Type': apiConfig.ContentType,
																														'Cache-Control': apiConfig.CacheControl
																													}
																									);

export const revoke = tokens 									=> fetchApi(
																													endPoints.revoke,
																													{},
																													{ tokens },
																													'POST'
																									);
