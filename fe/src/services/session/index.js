import store from '../../store';
import * as api from './api';
import * as selectors from './selectors';
import * as actionCreators from './actions';
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
				throw 'error'
			};
			return response.text()
		})
		.then((responseText) => {
			//console.log(responseText);
			return JSON.parse(responseText);
		})
		.then((responseJSON) => {
			//console.log(responseJSON);
			//dispatch to update the state
			const tokens = selectors.get();
			//authenticated resides in tokens objct, probably should be moved to customer
		   store.dispatch(actionCreators.update({"tokens": responseJSON}));
			 console.log(store.getState());
		})
		.then(onRequestSuccess)
		.catch(onRequestFailed);
	}

	const  onRequestSuccess = (response) => {
		console.log('the request was successful');
		console.log(response);
	};

	// const  persistTokens = (tokens) => {
	// 	store.dispatch(actionCreators.update({ tokens, "tokens": tokens }));
	// 	setSessionTimeout(tokens.expires_in);
	// }

export const revoke = () => {
	const session = selectors.get();
	return api.revoke(Object.keys(session.tokens).map(tokenKey => ({
		type: session.tokens[tokenKey].type,
		value: session.tokens[tokenKey].value,
	})))
	.then(clearSession())
	.catch(() => {});
};


export const clearSession = () => {
	clearTimeout(sessionTimeout);
	store.dispatch(actionCreators.update(initialState));
};

export const refreshToken = () => {
	const session = selectors.get();

	if (!session.tokens.refresh_token || !session.userName) {
		return Promise.reject();
	}

	return api.refresh(session.tokens.refresh_token)
	.then(onRequestSuccess)
	.catch(onRequestFailed);
};

const onRequestFailed = (exception) => {
	clearSession();
};
