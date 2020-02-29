import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/Cart/UserName/',
};

export const findByUserName = (userName) => fetchApi(
																										endPoints.get + userName,
																										{},
																										{},
																										'GET',
																										{}
																										);
