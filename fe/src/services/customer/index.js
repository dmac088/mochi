
import store from '../../store';
import * as session from '../session';
import * as api from './api';
import * as actionCreators from './actions';
import { initialState } from './reducer';



	export const findByUserName = (userName) =>
		api.findByUserName(userName)
		.then(onRequestSuccess)
		.catch(onRequestFailed);


	export const createNewCustomer = (customer) => {
		console.log('creating a new customer');
		 api.createNewCustomer(customer)
		.then((response) => {
				return response.text()
		 })
		 .then((responseText) => {
			 	return JSON.parse(responseText)
		 })
		 .then((responseJSON) => {
			 if(responseJSON.status === 500) {
				 console.log(responseJSON.message);
				 throw responseJSON.message
			 }
			 console.log('customer creation status = ' + responseJSON.message)
		 })
		.then(() => {
				session.authenticate(customer)
		})
		.then(onRequestSuccess)
		.catch(onRequestFailed);
	};

	export const clearCustomer = () => {
		store.dispatch(actionCreators.update(initialState));
	};

	const onRequestSuccess = (response) => {
		console.log('request successfully completed!');
	};

const onRequestFailed = (exception) => {
	console.log('request failed!');
	session.clearSession();
	//throw exception;
};
