import store from '../../store';
import * as api from './api';
import * as selectors from './selectors';
import * as actionCreators from './actions';
import { initialState } from './reducer';


	export const findByUserName = (userName) =>
		api.findByUserName(userName)
		//injection of a function reference means the function behaves
		//as if it were nested within the parentheses
		.then(onRequestSuccess)
		.catch(onRequestFailed);


	export const createNewCustomer = (customer) => {
		console.log(customer);
		return api.createNewCustomer(customer);
	};


	const onRequestSuccess = (response) => {
		 //we capture our response errors here and act accordingly
		 console.log('onRequestSuccess');
		 // if (response.status === 400) {
			//  console.log('Invalid username or password');
			//  clearSession();
			//  return;
		 // };

		 //response is an object of type promise
		 //we call the text function to execute the promise
		 response.text()
		 .then((responseText) => {
		 	 return responseText.JSON;
		 })
		 .then((responseJSON) => {
		//	 console.log(responseJSON);
		//	 console.log(actionCreators.update({ responseJSON, customer: responseJSON }));
			 store.dispatch(actionCreators.update({ responseJSON, customer: responseJSON }));
		 })
		 .catch((e) => {
	 	 			console.log(e);
	   });
	};



const onRequestFailed = (exception) => {
	throw exception;
};
