
import store from '../../store';
import * as api from './api';
import * as actionCreators from './actions';


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
			return item.productDTO.id === productID;
		});
	}

	export const addToCart = (cart, selectedProducts) => {
		let cartCopy = ...cart
		let productID = selectedProducts.id;
		let productQty = selectedProducts.quantity;
		if (checkProduct(cartCopy, productID)) {
	  //increment the quantity of the product in the cart
	    console.log("incrementing product quantity");
	    let index = cartCopy.items.findIndex(x => x.id === productID);
	    cartCopy.items[index].quantity =
	    Number(cartCopy.items[index].quantity) + Number(productQty);
	  } else {
	    //add the product to the cart
	    console.log('adding to cart....');
	    cartCopy.items.push(selectedProducts);
	  };

		cartCopy.totalItems = sumTotalItems(cartCopy);
		cartCopy.totalAmount = sumTotalAmount(cartCopy);
	  persistCart(cart);
	}

	export const removeFromCart = (cart, productId) => {
			let cartCopy = {...cart}
			if (checkProduct(cartCopy, productId)) {
				console.log('removing item from cart....');
				var filtered = cartCopy.items.filter(function(value, index, arr){
																			return value.productDTO.id !== productId;
																		});
			}
			cartCopy.items = filtered;
			cartCopy.totalItems = sumTotalItems(cartCopy);
			cartCopy.totalAmount = sumTotalAmount(cartCopy);
		  persistCart(cartCopy);
	}

	export const updateQuantity = (cart, productId, qty) => {
		let cartCopy = {...cart}
		let index = cartCopy.items.findIndex(x => x.productDTO.id === Number(productId));
		cartCopy.items[index].quantity = Number(cartCopy.items[index].quantity) + Number(qty);
		persistCart(cartCopy);
	}

	export const sumTotalItems = (cart) => {
	  let total = 0;
	  total = cart.items.length;
	  return total;
  }

  export const sumTotalAmount = (cart) => {
	  let total = 0;
	  for (var i = 0; i < cart.items.length; i++) {
	  total += cart.items[i].price * parseInt(cart.items[i].quantity);
	  }
	  return total;
 	}

	export const emptyCart = () => {
		console.log('emptyCart....');
		persistCart({items:[]});
	}
