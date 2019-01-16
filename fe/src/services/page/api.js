import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
	category: '/cat/',
	page: '/page/',
	size: '/size/',
	sort: '/sortBy/',
};

const sortSelector = {
        1:"productDesc",
        2:"productRrp"
      };

export const findAll = (locale, categoryId, page, size, sort) => fetchApi(
																																	endPoints.get + locale
																																+ endPoints.category + categoryId
																																+ endPoints.page + page
																																+ endPoints.size + size
																																+ endPoints.sort + sortSelector[sort],
																																{},
																																{},
																																'GET',
																																{}
																															);
