import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/ProductCategory/',
};

export const get = (locale) => 	fetchApi(endPoints.get + locale,
																				 {},
																				 {},
																				 'GET',
																				 {

																				 }
																			 );
