import store from '../../store';
import * as customerApi from './api';
import * as api from '../api';
import * as selectors from './selectors';
import * as actionCreators from './actions';
import { initialState } from './reducer';


	export const findByUserName = (userName) =>
		customerApi.findByUserName(userName)
		//injection of a function reference means the function behaves
		//as if it were nested within the parentheses
		.then(onRequestSuccess)
		.catch(onRequestFailed);


	export const createNewCustomer = (customer) => {
		return customerApi.createNewCustomer(customer).then((response) => {
			return response.text();
			//return session.authenticate(this.state.customer.userName,
			//we are using local state here since
			//our input text boxes write to local state and then
			//persist directly to database to create a new customer, not to redux (yet)
			//we poplulare reduct by making another call to the api
			//either using the userName in local state or returned party id in responseText
			//for this we use the /services/customer/index.js which must be imported
			//we need the access token from the redux store to make the subsequen API calls
			//this can be gotten by

			//  console.log(this.props.user);
			//  console.log(this.props.customer);
			//  console.log(this.props.tokens);

			//console.log(customerService.findByuserName(this.state.customer.partyUser.userName,
			//this.state.customer.partyUser.password));



			//we can reset local state, no impact to global redux state
			//const routeStack = this.props.navigator.getCurrentRoutes();
			//this.props.navigator.jumpTo(routeStack[3]);

	}).then((responseText) => {
			console.log(responseText);
			return JSON.parse(responseText);
	})
	.catch((exception) => {
		// Displays only the first error message
		const error = api.exceptionExtractError(exception);
		const newState = {
			isLoading: false,
			...(error ? { error } : {}),
		};
			this.setState(newState);
	});
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
