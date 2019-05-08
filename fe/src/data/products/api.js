import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/'
};

export const get = (locale, currency) => 	fetchApi(
																					 endPoints.get,
																					 payload,
																					 {},
																					 'GET',
																					 {}
																				 	);


export const getMaxPrice = (locale, currency, category, selectedFacets = [])  => fetchApi(
																					endPoints.get+
																					locale +
																					'/' + currency +
																					'/' + category +
																					'/maxprice',
																					selectedFacets,
																					{},
																					'POST',
																					{"Content-Type": "application/json"}
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

export const findByCategory = (locale, currency, category, brand, maxPrice, page = 0, size = 10, sortBy = 'nameAsc', selectedFacets = []) => fetchApi(
																					endPoints.get +
																					locale +
																					'/' + currency +
																					'/category/' + category +
																					'/maxPrice/' + maxPrice +
																					'/page/' + page +
																					'/size/' + size +
																					'/sortBy/' + sortBy,
																					selectedFacets,
																					{},
																					'POST',
																					{"Content-Type": "application/json"}
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
