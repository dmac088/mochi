import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/NavFacet/',
};


export const findAllChildreByCriteria = (locale, currency, categoryDesc, selectedBrands = []) => 	fetchApi(
                                                          																								endPoints.get +
                                                          																								locale + '/' +
                                                          																								currency + '/category/' +
                                                          																								categoryDesc,
                                                          																								selectedBrands,
                                                          																								{},
                                                          																								'POST',
                                                          																								{"Content-Type": "application/json"}
                                                          																							 );
