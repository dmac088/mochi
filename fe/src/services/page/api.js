import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
	page: '/page/',
	size: '/size/',
};

export const findAll = (locale, categoryId, page, size) => fetchApi(
																																endPoints.get + locale + '/cat/' + categoryId + endPoints.page + page + endPoints.size + size ,
																																{},
																																{},
																																'GET',
																																{}
																															);
