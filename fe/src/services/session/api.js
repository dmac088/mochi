import { fetchApi } from '../../services/api';
import apiConfig from '../../services/api/config';

const endPoints = {
	authenticate: '/oauth/token',
	revoke: '/oauth/token',
	refresh: '/oauth/token',
};

export const authenticate = (userName, password) => fetchApi(
																													endPoints.authenticate,
																													{},
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
																													{},
																													{
																														refresh_token: token,
																														grant_type: 'refresh_token',
																													},
																													'POST',
																													{
																														 Authorization: 'Basic ' + apiConfig.ClientId,
																														 'Content-Type': apiConfig.ContentType,
																													}
																												);

export const revoke = tokens 									=> fetchApi(
																													endPoints.revoke,
																													{},
																													{ tokens },
																													'POST'
																												);
