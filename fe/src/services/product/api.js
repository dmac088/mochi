import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
	page: '/page/0/size/5',
};

export const findAll = (locale) => fetchApi(
																						endPoints.get + locale + endPoints.page,
																						{},
																						{},
																						'GET',
																						{}
																					 );


export const findByCategory = (locale, categoryId) => fetchApi(
																						endPoints.get + locale + '/cat/' + categoryId + endPoints.page,
																						{},
																						{},
																						'GET',
																						{}
																						);
