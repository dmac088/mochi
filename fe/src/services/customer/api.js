import { fetchApi } from '../../services/api';
import apiConfig from '../../services/api/config';

const endPoints = {
	get: '/api/Customer/UserName/',
	signup: '/api/Customer/Signup'
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
																																	Authorization: 'Basic ' + apiConfig.ClientId,
																																	'Content-Type': 'application/x-www-form-urlencoded',
																																	'Cache-Control': 'no-cache'
																																}
																															);

export const createNewCustomer = (customer) => fetchApi(
																															endPoints.signup,
																															customer,
																															{},
																															'POST',
																															{
																															'Content-Type': 'application/json'
																															}
																											 );
