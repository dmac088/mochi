
import store from '../../store';
import * as api from './api';
import * as actionCreators from './actions';
import { initialState } from './reducer';


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
		//console.log(cart);
	 	store.dispatch(actionCreators.update({"cart": cart }));
	}
