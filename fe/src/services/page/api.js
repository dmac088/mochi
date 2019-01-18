import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Search/',
	search: '/SearchTerm/',
	page: '/Page/',
	size: '/Size/',
	sort: '/SortBy/',
};

const sortSelector = {
        1:"productDesc",
        2:"productRrp"
      };

export const findAll = (locale, searchTerm, page, size, sort) => fetchApi(
																																	endPoints.get + locale
																																+ endPoints.search + searchTerm
																																+ endPoints.page + page
																																+ endPoints.size + size
																																+ endPoints.sort + sortSelector[sort],
																																{},
																																{},
																																'GET',
																																{}
																															);
