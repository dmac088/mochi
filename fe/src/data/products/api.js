import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
	post: '/api/Product/',
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


export const findByCategory = (locale, currency, category, brand, maxPrice, page = 0, size = 10, sortBy = 'nameAsc') => fetchApi(
																				endPoints.post +
																				locale +
																				'/' + currency +
																				'/category/' + category +
																				//((brand) ? '/brand/' : '') +
																				//((brand) ? brand : '') +
																				'/maxPrice/' + maxPrice +
																				'/page/' + page +
																				'/size/' + size +
																				'/sortBy/' + sortBy,
																				{},
																				{},
																				'POST',
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
