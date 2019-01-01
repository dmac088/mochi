import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/ProductCategory/',
};

export const findAll = (locale) => 	fetchApi(
																				 endPoints.get + locale,
																				 {},
																				 {},
																				 'GET',
																				 {}
																			 );
