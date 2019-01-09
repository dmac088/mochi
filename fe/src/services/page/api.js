import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
	page: '/page/',
	size: '/size/',
};

export const findAll = (locale, page = 0, size = 5) => fetchApi(
																						endPoints.get + locale + endPoints.page + page + endPoints.size + size,
																						{},
																						{},
																						'GET',
																						{}
																					 );


export const findByCategory = (locale, categoryId, page = 0, size = 5) => fetchApi(
																																endPoints.get + locale + '/cat/' + categoryId + endPoints.page + page + endPoints.size + size ,
																																{},
																																{},
																																'GET',
																																{}
																															);
