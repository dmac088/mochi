import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Search/',
	search: '/SearchTerm/',
	category: /Category/,
	page: '/Page/',
	size: '/Size/',
	sort: '/SortBy/',
};

const sortSelector = {
        1:"productDesc",
        2:"productRrp"
      };

export const findAll = (locale, category, searchTerm, page, size, sort) => fetchApi(
																																	endPoints.get + locale
																																+ endPoints.category + category
																																+ endPoints.search + searchTerm
																																+ endPoints.page + page
																																+ endPoints.size + size
																																+ endPoints.sort + sortSelector[sort],
																																{},
																																{},
																																'GET',
																																{}
																															);
