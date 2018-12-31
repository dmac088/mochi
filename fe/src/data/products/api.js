import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
};

export const get = (locale) => 	fetchApi(endPoints.get,
																				 payload,
																				 {},
																				 'GET',
																				 {

																				 }
																			 );

export const findByCategory = (locale, categoryId) => fetchApi(
																				endPoints.get + locale + '/' + categoryId,
																				{},
																				{},
																				'GET',
																				{}
																				);
