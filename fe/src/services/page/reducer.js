import * as actionTypes from './actionTypes';

export const initialState = 					{
																				content: [
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
				...action.content,
			};
		default:
			return state;
	}
};
