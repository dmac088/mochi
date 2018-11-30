import { fetchApi } from '../../services/api';
import apiConfig from '../../services/api/config';

const endPoints = {
	get: '/Customer/UserName/',
	other: ''
};

export const findByUserName = (userName, password) => fetchApi(
																																endPoints.get + userName,
																																{},
																																{
																																	username: userName,
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

export const createNewCustomer = (userName, password, customer, token) => fetchApi(
																															endPoints.get + userName,
																															{},
																															{
																															},
																															'POST',
																															{
																															'Content-Type': 'application/json'
																															}
																															);
