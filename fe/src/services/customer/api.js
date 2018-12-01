import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Customer/UserName/',
	signup: '/api/Customer/Signup'
};

export const findByUserName = (token, userName) => fetchApi(
																																endPoints.get + userName,
																																{},
																																{},
																																'GET',
																																{
																																	Authorization: 'Bearer ' + token.value,
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
