import { fetchApi } from '../../services/api';
import apiConfig from '../../services/api/config';

const endPoints = {
	get: '/api/Person/',
	other: ''
};

export const findByUserName = (userName) => fetchApi(
																													endPoints.get + userName,
																													{},
																													{
																														username: email,
																														password: password,
																														grant_type: 'password'
																													},
																													'GET',
																													{
																															Authorization: 'Basic ' + apiConfig.clientId,
																															'Content-Type': 'application/x-www-form-urlencoded',
																															'Cache-Control': 'no-cache'
																													}
																								);
