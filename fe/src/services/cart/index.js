
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

	export const addCartItem = (cart, item) => {
		store.dispatch(actionCreators.addItem(cart, item))
	}

	export const updateCartItem = (cart, index, item) => {
		store.dispatch(actionCreators.updateItem(cart, index, item));
	}

	export const removeCartItem = (cart, productId) => {
		store.dispatch(actionCreators.removeItem(cart, productId));
	}

	export const updateCartTotals = (cart) => {
			store.dispatch(actionCreators.updateCartTotals(
								cart, sumTotalItems(cart.items), sumTotalAmount(cart.items)));
	}

	const checkProduct = (cart, productID) => {
		return cart.items.some(function(item) {
			return item.productDTO.productId === productID;
		});
	}

	export const addToCart = (cart, selectedProduct) => {
		let productId = selectedProduct.productDTO.productId;
		let productQty = selectedProduct.quantity;
		if (checkProduct(cart, productId)) {
	    let index = cart.items.findIndex(x => x.productDTO.productId === productId);
			let updatedItem = _.cloneDeep(cart.items[index]);
			updatedItem.quantity = updatedItem.quantity + Number(productQty);
			updateCartItem(cart, index, updatedItem);
	  } else {
	    //add the product to the cart
			addCartItem(cart, selectedProduct);
	  };
		updateCartTotals(cart);
	}

	export const removeFromCart = (cart, productId) => {
			if (checkProduct(cart, productId)) {
				removeCartItem(cart, productId);
			}
			updateCartTotals(cart);
	}

	export const updateQuantity = (cart, productId, qty) => {
		let cartClone = _.cloneDeep(cart);
		let index = cartClone.items.findIndex(x => x.productDTO.productId === Number(productId));
		cartClone.items[index].quantity = Number(cartClone.items[index].quantity) + Number(qty);
		updateCartTotals(cartClone);
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
		//persistCart({items:[]});
	}
