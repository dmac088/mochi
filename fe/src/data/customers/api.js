import { fetchApi } from '../../services/api';

const endPoints = {
	create: '/api/Person',
	get: '/api/Person/UserName',
};

export const create = payload => fetchApi(endPoints.create,
																					payload, {
																						'Content-Type': 'application/json'
																					},
																					'POST',
																					{}
																				);


export const get = payload => fetchApi(endPoints.get, payload, {}, 'GET', {});
