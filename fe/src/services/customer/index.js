
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
		 api.createNewCustomer(customer)
			.then(() => {
					session.authenticate(customer, onRequestFailed)
			})
			.then(onRequestSuccess)
			.catch(onRequestFailed);
	};

	export const clearCustomer = () => {
		store.dispatch(actionCreators.update(initialState));
	};

	export const persistCustomer = (customer) => {
	 	store.dispatch(actionCreators.update({"customer": customer }));
	}

	const onRequestSuccess = (response) => {
	};

	const onRequestFailed = (exception) => {
		session.clearSession();
		throw exception;
	};
