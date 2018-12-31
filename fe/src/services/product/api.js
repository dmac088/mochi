import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
	getByCategory: '/api/Product/',
};

export const findAll = (locale) => fetchApi(
																						endPoints.get + locale,
																						{},
																						{},
																						'GET',
																						{}
																					 );


export const findByCategory = (locale, categoryId) => fetchApi(
																						endPoints.getByCategory + locale + '/' + categoryId,
																						{},
																						{},
																						'GET',
																						{}
																						);
