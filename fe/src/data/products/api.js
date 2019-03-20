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


export const findPreviewByCategory = (locale, categoryId) => fetchApi(
																				endPoints.get + locale + '/categoryId/' + categoryId + '/preview',
																				{},
																				{},
																				'GET',
																				{}
																				);

export const findByCategory = (locale, categoryDesc, page = 0, size = 10, sortBy = 'productRrp') => fetchApi(
																				endPoints.get + locale + '/categoryDesc/' + categoryDesc + '/page/' + page + '/size/' + size + '/sortBy/' + sortBy,
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
