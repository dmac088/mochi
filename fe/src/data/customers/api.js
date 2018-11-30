import { fetchApi } from '../../services/api';

const endPoints = {
	create: '/api/Customer/Signup',
	get: '/api/Person/UserName',
};

export const create = payload => fetchApi(endPoints.create,
																					payload,
																					{

																					},
																					'POST',
																					{
																						'Content-Type': 'application/json'
																					}
																				);


export const get = payload => 	fetchApi(endPoints.get,
																				 payload,
																				 {},
																				 'GET',
																				 {

																				 }
																			 );
