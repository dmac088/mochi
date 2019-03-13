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

export const findByCategory = (locale, categoryId) => fetchApi(
																				endPoints.get + locale + '/' + categoryId,
																				{},
																				{},
																				'GET',
																				{}
																				);

export const findPreviewByCategory = (locale, categoryId) => fetchApi(
																				endPoints.get + locale + '/cat/' + categoryId + '/preview',
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
