import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Brand/',
	category: '/Category/'
};

export const findAll = (locale, currency, category) => {
																												return fetchApi(
																													endPoints.get
																													+ locale
																													+ '/'
																													+ currency
																													+ endPoints.category
																													+ category,
																													{},
																													'GET',
																													{"Content-Type": "application/json"}
																												)
																											};
