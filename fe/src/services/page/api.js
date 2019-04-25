import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Search/',
	search: '/SearchTerm/',
	category: /Category/,
	maxPrice: /maxPrice/,
	page: '/Page/',
	size: '/Size/',
	sort: '/SortBy/',
};

export const findAll = (locale, currency, category, searchTerm, maxPrice, page, size, sort, selectedCategoryFacets) => {
																																return fetchApi(
																																	endPoints.get + locale
																																+ '/' + currency
																																+ endPoints.category + category
																															//	+ endPoints.maxPrice + maxPrice
																																+ endPoints.search + searchTerm
																																+ endPoints.page + page
																																+ endPoints.size + size
																																+ endPoints.sort + sort,
																																selectedCategoryFacets,
																																{},
																																'POST',
																																{"Content-Type": "application/json"}
																															)
																														};
