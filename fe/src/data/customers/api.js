import { fetchApi } from '../../services/api';

const endPoints = {
	create: '/api/Party',
	get: '/api/Party/UserName',
};

export const create = payload => fetchApi(endPoints.create, payload, {},'POST',{});


export const get = payload => fetchApi(endPoints.get, payload, {}, 'GET', {});
