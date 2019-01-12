
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
	//	console.log('this is the item');
		//	console.log(item);
			return item.productDTO.id === productID;
		});
	}

	export const addToCart = (cart, selectedProducts) => {
		let productID = selectedProducts.id;
		let productQty = selectedProducts.quantity;
		if (checkProduct(cart, productID)) {
	  //increment the quantity of the product in the cart
	    console.log("incrementing product quantity");
	    let index = cart.items.findIndex(x => x.id === productID);
	    cart.items[index].quantity =
	    Number(cart.items[index].quantity) + Number(productQty);
	  } else {
	    //add the product to the cart
	    console.log('adding to cart....');
	    cart.items.push(selectedProducts);
	  };

		cart.totalItems = sumTotalItems(cart);
		cart.totalAmount = sumTotalAmount(cart);
	  persistCart(cart);
	}

	export const removeFromCart = (cart, productId) => {
			if (checkProduct(cart, productId)) {
				console.log('removing item from cart....');
				var filtered = cart.items.filter(function(value, index, arr){
																			return value.productDTO.id !== productId;
																		});
			}
			let cartCopy = {...cart}
			cartCopy.items = filtered;
			cartCopy.totalItems = sumTotalItems(cartCopy);
			cartCopy.totalAmount = sumTotalAmount(cartCopy);
		  persistCart(cartCopy);
	}

	export const updateQuantity = (cart, selectedProduct, qty) => {
		let index = cart.items.findIndex(x => x.id === selectedProduct.id);
		cart.items[index].quantity =
		Number(cart.items[index].quantity) + Number(qty);
		persistCart(cart);
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
