import store from '../../store';
import * as customerApi from './api';
import * as api from '../api';
import * as selectors from './selectors';
import * as actionCreators from './actions';
import { initialState } from './reducer';


	export const findByUserName = (userName) =>
		customerApi.findByUserName(userName)
		.then(onRequestSuccess)
		.catch(onRequestFailed);


	export const createNewCustomer = (customer) => {
		console.log('creating a new customer');
		return customerApi.createNewCustomer(customer).then((response) => {
			return response.text()
		}).then((responseText) => {
				return JSON.parse(responseText)
		})
		.catch((exception) => {
			const error = api.exceptionExtractError(exception);
			const newState = {
				isLoading: false,
				...(error ? { error } : {}),
			};
				this.setState(newState);
		});
	};


	const onRequestSuccess = (response) => {
		 response.text()
		 .then((responseText) => {
		 	 return responseText.JSON;
		 })
		 .then((responseJSON) => {
			 store.dispatch(actionCreators.update({ responseJSON, customer: responseJSON }));
		 })
		 .catch((e) => {
	 	 			console.log(e);
	   });
	};



const onRequestFailed = (exception) => {
	throw exception;
};
