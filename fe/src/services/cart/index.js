
import store from '../../store';
import * as api from './api';
import * as actionCreators from './actions';
import _ from 'lodash';
import * as cartSelector from './selectors';

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

	export const removeCartItem = (cart, productUPC) => {
		store.dispatch(actionCreators.removeItem(cart, productUPC));
	}

	export const updateCartTotals = () => {
		const cart = cartSelector.get();
		store.dispatch(actionCreators.updateCartTotals(
								cart, sumTotalItems(cart.items), sumTotalAmount(cart.items)));
	}

	export const updateCartItems = (items) => {
		const cart = cartSelector.get();
		store.dispatch(actionCreators.updateCartItems(
							  cart, items));
	}

	const checkProduct = (cart, productUPC) => {
		return cart.items.some(function(item) {
			return item.productUPC === productUPC;
		});
	}

	export const addToCart = (cart, selectedProduct, callback) => {
		let { productUPC, quantity } = selectedProduct;
		if (checkProduct(cart, productUPC)) {
	    let index = cart.items.findIndex(x => x.productUPC === productUPC);
			let updatedItem = _.cloneDeep(cart.items[index]);
			updatedItem.quantity = updatedItem.quantity + Number(quantity);
			updateCartItem(cart, index, updatedItem);
	  } else {
	    //add the product to the cart
			addCartItem(cart, selectedProduct);
	  };
		updateCartTotals();
		callback();
	}

	export const removeFromCart = (cart, productUPC) => {
		if (checkProduct(cart, productUPC)) {
			removeCartItem(cart, productUPC);
		}
		updateCartTotals();
	}

	export const updateQuantity = (cart, productId, qty) => {
		let index = cart.items.findIndex(x => x.productId === Number(productId));
		let updatedItem =  _.cloneDeep(cart.items[index]);
		updatedItem.quantity = Number(cart.items[index].quantity) + Number(qty);
		updateCartItem(cart, index, updatedItem);
		updateCartTotals();
	}

	export const sumTotalItems = (items) => {
	  let total = 0;
	  total = items.length;
	  return total;
  }

  export const sumTotalAmount = (items) => {
	  let total = 0;
	  for (var i = 0; i < items.length; i++) {
	  total += items[i].productMarkdown * parseInt(items[i].quantity);
	  }
	  return total;
 	}

	export const emptyCart = () => {
		console.log('emptyCart....');
		//persistCart({items:[]});
	}
