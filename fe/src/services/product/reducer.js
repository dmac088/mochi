import * as actionTypes from './actionTypes';

export const initialState = 					{	products: [
																					{
																				        "productId": null,
																				        "productUPC": null,
																				        "productCreateDt": null,
																				        "lclCd": null,
																				        "productRrp": null,
																				        "productDesc": null,
																				        "productImage": null,
																				        "productCategory": null
																				    }
																				]
																			};

export const reducer = (state = initialState, action) => {
	console.log('product reducer was fired!');

	switch (action.type) {
		case actionTypes.UPDATE:
			return {
				...action.products,
			};
		default:
			return state;
	}
};
