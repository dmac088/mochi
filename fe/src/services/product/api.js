import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Product/',
};

export const findAll = (locale) => fetchApi(
																						endPoints.get + locale,
																						{},
																						{},
																						'GET',
																						{}
																					 );
