import store from '../../store';
import * as api from './api';
import * as customerApi from '../customer/api';
import * as sessionSelectors from './selectors';
import * as tokenActionCreators from './actions';
import * as customerActionCreators from '../customer/actions';
import * as customerService from '../customer';
import * as sessionReducer from './reducer';
import _ from 'lodash';

const SESSION_TIMEOUT_THRESHOLD = 300; // Will refresh the access token 5 minutes before it expires

let sessionTimeout = null;

// const setSessionTimeout = (duration) => {
// 	clearTimeout(sessionTimeout);
// 	sessionTimeout = setTimeout(
// 		refreshToken, // eslint-disable-line no-use-before-define
// 		duration
// 		//(duration - SESSION_TIMEOUT_THRESHOLD) * 1000
// 	);
// };


export const authenticate = (customer, onSuccess, onFailure) => {
		 api.authenticate(customer.userName, customer.password)
		.then((response) => {
			if(response.status === 400) {
				console.log('Invalid username or password');
				onFailure();
				throw response;
			};
			return response.text()
		})
		.then((responseText) => {
			return JSON.parse(responseText);
		})
		.then((responseJSON) => {
			persistTokens(responseJSON);
			return responseJSON
		})
		.then(() => {
			customerApi.findByUserName(customer.userName)
			.then((response) => {
				return response.text();
			})
			.then((responseText) => {
				return JSON.parse(responseText);
			})
			.then((responseJSON) => {
				customerService.persistCustomer(responseJSON);
			})
		})
		.then(onSuccess)
		.then(onRequestSuccess)
		.catch(onRequestFailed);
	}

	const  onRequestSuccess = (response) => {
		console.log('the request was successful');
	};

	export const  persistTokens = (tokens) => {
	 	store.dispatch(tokenActionCreators.update({"tokens": tokens}));
	}

	export const revoke = () => {
		const session = sessionSelectors.get();
		return api.revoke(Object.keys(session.tokens).map(tokenKey => ({
			type: session.tokens[tokenKey].type,
			value: session.tokens[tokenKey].value,
		})))
		.then(clearSession())
		.catch(() => {});
	};


	export const clearSession = () => {
		clearTimeout(sessionTimeout);
		store.dispatch(tokenActionCreators.update({tokens: {}}));
		store.dispatch(customerActionCreators.update({customer: {}}));
	};

	export const refreshToken = () => {
		console.log("refreshToken");

		if (!sessionSelectors.get().tokens.refresh_token || !sessionSelectors.get().tokens.userName) {
			persistTokens(sessionReducer.initialState);
			return Promise.reject();
		}
		return api.refresh(sessionSelectors.get().tokens.refresh_token)
		.then((response) => {
			return response.text();
		})
		.then((responseText) => {
			 return JSON.parse(responseText);
		})
		.then ((responseJSON) => {
			const newstate = _.cloneDeep(sessionSelectors.get().tokens);
			newstate['access_token'] =  responseJSON.access_token;
			newstate['refresh_token'] = responseJSON.refresh_token;
			newstate['authenticated'] = responseJSON.authenticated;
			newstate['expires_in'] = responseJSON.expires_in;
			newstate['token_type'] = responseJSON.token_type;
			persistTokens(newstate);
		})
		.then(onRequestSuccess)
		.catch(onRequestFailed);
	};

	const onRequestFailed = (exception) => {
		clearSession();
		console.log(exception);
	};
