import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
};

export const get = (locale) => 	fetchApi(
																				 endPoints.get,
																				 payload,
																				 {},
																				 'GET',
																				 {

																				 }
																			 );

export const findById = (locale, productId) => fetchApi(
																				endPoints.get + locale + '/id/' + productId,
																				{},
																				{},
																				'GET',
																				{}
																				);


export const findByCategory = (locale, category, brand, page = 0, size = 10, sortBy = 'productRrp') => fetchApi(
																				endPoints.get + locale +
																				'/categoryDesc/' + category +
																				((brand) ? '/brand/' : '') +
																				((brand) ? brand : '') +	
																				'/page/' + page +
																				'/size/' + size +
																				'/sortBy/' + sortBy,
																				{},
																				{},
																				'GET',
																				{}
																			);

export const findAllFeatured = (locale) => fetchApi(
																				endPoints.get + locale + '/featured',
																				{},
																				{},
																				'GET',
																				{}
																				);
