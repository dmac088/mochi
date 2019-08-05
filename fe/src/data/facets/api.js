import { fetchApi } from '../../services/api';

const endPoints = {
	get: '/api/NavFacet/',
};


export const findAllChildrenByCriteria = (locale, currency, categoryDesc, selectedFacets) => 						fetchApi(
                                                          																								endPoints.get +
                                                          																								locale + '/' +
                                                          																								currency + '/category/' +
                                                          																								categoryDesc,
                                                          																								selectedFacets,
                                                          																								{},
                                                          																								'POST',
                                                          																								{"Content-Type": "application/json"}
                                                          																							 );
