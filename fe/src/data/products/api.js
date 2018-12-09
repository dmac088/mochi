import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/HKG',
};

export const get = payload => 	fetchApi(endPoints.get,
																				 payload,
																				 {},
																				 'GET',
																				 {

																				 }
																			 );
