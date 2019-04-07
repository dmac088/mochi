import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/ProductCategory/',
};

export const findAll = (locale, currency) => 	fetchApi(
																				 endPoints.get +
																				 locale + '/' +
																				 currency,
																				 {},
																				 {},
																				 'GET',
																				 {}
																			 );

export const findAllForLevel = (locale, currency, level) => 	fetchApi(
																			 		endPoints.get +
																				  locale + '/' +
 																				  currency + '/level/' +
																					level,
																			 		{},
																			 		{},
																			 		'GET',
																			 		{}
																			 	);

export const findAllPreview = (locale, currency) => 	fetchApi(
																					endPoints.get +
																					locale + '/' +
 																				 	currency + '/preview/',
																					{},
																					{},
																					'GET',
																					{}
																					);

export const findByDesc = (locale, currency, desc) => 	fetchApi(
																					endPoints.get + '/' +
 																				 	currency +
																					'/desc/' + desc,
																					{},
																					{},
																					'GET',
																					{}
																				);
