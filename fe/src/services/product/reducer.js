import * as actionTypes from './actionTypes';

export const initialState = 					{
																				items: [
																					{
																				        "productId": '',
																				        "productUPC": '',
																				        "productCreateDt": '',
																				        "lclCd": '',
																				        "productRrp": '',
																				        "productDesc": '',
																				        "productImage": '',
																				        "productCategory": ''
																				    }
																				]
																			};

export const reducer = (state = initialState, action) => {
	switch (action.type) {
		case actionTypes.UPDATE:
			return {
				...action.items,
			};
		default:
			return state;
	}
};
