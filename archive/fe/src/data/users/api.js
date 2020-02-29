import { fetchApi } from '../../services/api';

const endPoints = {
	create: '/users',
	get: '/users',
};

//only crud methods go here, anything requiring more complex logic
//goes in the service class in /services/customer/index.js

export const create = payload => fetchApi(endPoints.create, payload, {}, 'post');
