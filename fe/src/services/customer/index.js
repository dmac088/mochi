
import store from '../../store';
import * as session from '../session';
import * as customerApi from './api';
import * as actionCreators from './actions';
import { initialState } from './reducer';



	export const findByUserName = (userName) =>
		customerApi.findByUserName(userName)
		.then(onRequestSuccess)
		.catch(onRequestFailed);


	export const createNewCustomer = (customer) => {
		console.log('creating a new customer');
		 customerApi.createNewCustomer(customer)
		.then((response) => {
			  if(response.status === 500) {
					throw response.text();
				};
				return response.text()
		 })
		 .then((responseText) => {
			 	return JSON.parse(responseText)
		 })
		 .then((responseJSON) => {
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
	session.clearSession();
	throw exception;
};
