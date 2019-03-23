import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/ProductCategory/',
};

export const findAll = (locale) => 	fetchApi(
																				 endPoints.get + locale,
																				 {},
																				 {},
																				 'GET',
																				 {}
																			 );

export const findAllForLevel = (locale, level) => 	fetchApi(
																			 		endPoints.get + locale + '/level/' + level,
																			 		{},
																			 		{},
																			 		'GET',
																			 		{}
																			 	);

export const findAllPreview = (locale) => 	fetchApi(
																					endPoints.get + locale + '/preview/',
																					{},
																					{},
																					'GET',
																					{}
																					);

export const findByDesc = (locale, desc) => 	fetchApi(
																					endPoints.get + locale + '/desc/' + desc,
																					{},
																					{},
																					'GET',
																					{}
																				);
