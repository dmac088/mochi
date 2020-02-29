import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Customer/UserName/',
	signup: '/api/Customer/Signup'
};

export const findByUserName = (userName) => fetchApi(
																															endPoints.get + userName,
																															{},
																															{},
																															'GET',
																															{}
																										);

export const createNewCustomer = (customer) => fetchApi(
																															endPoints.signup,
																															customer,
																															{},
																															'POST',
																															{"Content-Type": "application/json"}
																											 );
