
import store from '../../store';
import * as api from './api';
import * as actionCreators from './actions';
import _ from 'lodash';


	export const findByUserName = (userName) =>
		api.findByUserName(userName)
		.then(onRequestSuccess)
		.catch(onRequestFailed);

	const onRequestSuccess = (response) => {
	};

	const onRequestFailed = (exception) => {
		throw exception;
	};

	export const  persistCart = (cart) => {
	 	store.dispatch(actionCreators.update({"cart": cart }));
	}

	const checkProduct = (cart, productID) => {
		return cart.items.some(function(item) {
			return item.productDTO.productId === productID;
		});
	}

	export const addToCart = (cart, selectedProduct) => {
		let cartClone = _.cloneDeep(cart)
		let productID = selectedProduct.productDTO.productId;
		let productQty = selectedProduct.quantity;

		if (checkProduct(cartClone, productID)) {
	  //increment the quantity of the product in the cart
	    let index = cartClone.items.findIndex(x => x.productDTO.productId === productID);
	    cartClone.items[index].quantity = Number(cartClone.items[index].quantity) + Number(productQty);
	  } else {
	    //add the product to the cart
	    cartClone.items.push(selectedProduct);
	  };

		cartClone.totalItems = sumTotalItems(cartClone.items);
		cartClone.totalAmount = sumTotalAmount(cartClone.items);
	  persistCart(cartClone);
	}

	export const removeFromCart = (cart, productId) => {
			if (checkProduct(cart, productId)) {
				var filtered = cart.items.filter(function(value, index, arr){

																			return value.productDTO.productId !== productId;
																		});
			}
			let newCart = {

														items: filtered,
														totalItems: sumTotalItems(filtered),
														totalAmount: sumTotalAmount(filtered),
										};
		  persistCart(newCart);
	}

	export const updateQuantity = (cart, productId, qty) => {
		let cartClone = _.cloneDeep(cart);
		let index = cartClone.items.findIndex(x => x.productDTO.productId === Number(productId));
		cartClone.items[index].quantity = Number(cartClone.items[index].quantity) + Number(qty);
		cartClone.totalItems = sumTotalItems(cartClone.items);
		cartClone.totalAmount = sumTotalAmount(cartClone.items);
		persistCart(cartClone);
	}

	export const sumTotalItems = (items) => {
	  let total = 0;
	  total = items.length;
	  return total;
  }

  export const sumTotalAmount = (items) => {
	  let total = 0;
	  for (var i = 0; i < items.length; i++) {
	  total += items[i].productDTO.productRrp * parseInt(items[i].quantity);
	  }
	  return total;
 	}

	export const emptyCart = () => {
		console.log('emptyCart....');
		persistCart({items:[]});
	}
