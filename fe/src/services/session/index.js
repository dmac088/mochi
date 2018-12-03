import store from '../../store';
import * as api from './api';
import * as customerApi from '../customer/api';
import * as sessionSelectors from './selectors';
import * as tokenActionCreators from './actions';
import * as customerActionCreators from '../customer/actions';
import * as customerSelectors from '../customer/selectors';
import { initialState } from './reducer';

const SESSION_TIMEOUT_THRESHOLD = 300; // Will refresh the access token 5 minutes before it expires

let sessionTimeout = null;

const setSessionTimeout = (duration) => {
	clearTimeout(sessionTimeout);
	sessionTimeout = setTimeout(
		refreshToken, // eslint-disable-line no-use-before-define
		(duration - SESSION_TIMEOUT_THRESHOLD) * 1000
	);
};


export const authenticate = (customer) => {
		 api.authenticate(customer.userName, customer.password)
		.then((response) => {
			if(response.status === 400) {
				console.log('Invalid username or password')

				//we force the catch when the username or password are invalid
				//but the message could/should come from the server
				throw 'error'
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
				store.dispatch(customerActionCreators.update({"customer": responseJSON}));
			})
		})
		.then(() => {
			return 'success'
		})
		.then(onRequestSuccess)
		.catch(onRequestFailed);
	}

	const  onRequestSuccess = (response) => {
		console.log('the request was successful');
	};

	const  persistTokens = (tokens) => {
	 	store.dispatch(tokenActionCreators.update({ tokens, "tokens": tokens }));
	 	setSessionTimeout(tokens.expires_in);
	}

export const revoke = () => {
	const session = sessionSelectors.get();
	const customer = customerSelectors.get();
	return api.revoke(Object.keys(session.tokens).map(tokenKey => ({
		type: session.tokens[tokenKey].type,
		value: session.tokens[tokenKey].value,
	})))
	.then(clearSession())
	.catch(() => {});
};


export const clearSession = () => {
	clearTimeout(sessionTimeout);
	store.dispatch(tokenActionCreators.update(initialState));
};

export const refreshToken = () => {
	const session = sessionSelectors.get();
	console.log(store.getState());
	if (!session.tokens.refresh_token || !session.userName) {
		return Promise.reject();
	}

	console.log('refresh token = ' + session.tokens.refresh_token);
	return api.refresh(session.tokens.refresh_token)
	.then(onRequestSuccess)
	.catch(onRequestFailed);
};

const onRequestFailed = (exception) => {
	clearSession();
	console.log(exception);
};
