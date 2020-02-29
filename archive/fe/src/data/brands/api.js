import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Brand/',
	post: '/api/Brand/',
};

export const get = (locale, currency) => 	fetchApi(
																						 endPoints.get,
																						 payload,
																						 {},
																						 'GET',
																						 {}
																					);

export const findById = (locale, currency, brandId) => fetchApi(
																													endPoints.get +
																													locale +
																													'/' + currency +
																													'/id/' + productId,
																													{},
																													{},
																													'GET',
																													{}
																											 );


export const findByBrandCategory = (locale, currency, category) => fetchApi(
																																	endPoints.get +
																											 						locale +
																											 						'/' + currency +
																											 						'/category/' + category,
																											 						{},
																											 						{},
																											 						'GET',
																											 						{}
																											 				);

export const findByProductCategory = (locale, currency, parentCategory, selectedCategories = []) => fetchApi(
																																endPoints.get +
																																locale +
																																'/' + currency +
																																'/category/' + parentCategory,
																																selectedCategories,
																																{},
																																'POST',
																																{"Content-Type": "application/json"}
																															);
