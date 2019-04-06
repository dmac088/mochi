import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
};

export const get = (locale, currency) => 	fetchApi(
																				 endPoints.get,
																				 payload,
																				 {},
																				 'GET',
																				 {

																				 }
																			 );

export const findById = (locale, currency, productId) => fetchApi(
																				endPoints.get +
																				locale +
																				'/' + currency +
																				'/id/' + productId,
																				{},
																				{},
																				'GET',
																				{}
																				);


export const findByCategory = (locale, currency, category, brand, page = 0, size = 10, sortBy = 'priceAsc') => fetchApi(
																				endPoints.get +
																				locale +
																				'/' + currency +
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

export const findAllFeatured = (locale, currency) => fetchApi(
																				endPoints.get +
																				locale +
																				'/' + currency +
																				'/featured',
																				{},
																				{},
																				'GET',
																				{}
																				);
