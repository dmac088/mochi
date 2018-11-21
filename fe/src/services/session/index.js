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

const clearSession = () => {
	clearTimeout(sessionTimeout);

	//set the state back to initial state
	store.dispatch(actionCreators.update(initialState));
};


const formatTokenResponse = (accessToken, refreshToken, user, expires_in) => ({
	tokens: [{
		type: 'access',
		value: accessToken,
		expiresIn: expires_in,
	},
	{
		type: 'refresh',
		value: refreshToken,
	}],
	user: {
		id: user,
	},
});


const  onRequestSuccess = async (response) => {
	 console.log('onRequestSuccess');
	 const body = await response.text();

	 const reformTokens = formatTokenResponse(
												 			JSON.parse(body).access_token,
												 			JSON.parse(body).refresh_token,
												 			JSON.parse(body).username,
												 			JSON.parse(body).expires_in
												 	);

	//console.log(reformTokens);


	//copy previous state (immutable) and overwrite the tokens, don't confuce with reducer
	//this is simply javascript array.reduce() with two parameters
	//item will overwrite previous state
	const tokens = 				reformTokens.tokens.reduce(
																											(prev, item) => ({
																												 	...prev,
																												 	[item.type]: item,
													 												 		}),
																								{});

	console.log(tokens);

	//update state using dispatch function and passing in new copy of state
	//for both tokens array and user object
	store.dispatch(actionCreators.update({ tokens, user: reformTokens.user }));

	//get the current state (singleton)
	const session = selectors.get();
	//console.log('the session state has set the refresh token to = ' + session.tokens.refresh.value);
	//console.log('the session state has set the access token to = ' + session.tokens.access.value);
	setSessionTimeout(tokens.access.expiresIn);
};

const onRequestFailed = (exception) => {
	clearSession();
	throw exception;
};

export const refreshToken = () => {
	console.log("called refresh token");
	const session = selectors.get();

	if (!session.tokens.refresh.value || !session.user.id) {
		return Promise.reject();
	}
	return api.refresh(session.tokens.refresh)
	.then(onRequestSuccess)
	.catch(onRequestFailed);
};

export const authenticate = (email, password) =>
	api.authenticate(email, password)
	.then(onRequestSuccess)
	.catch(onRequestFailed);

export const revoke = () => {
	const session = selectors.get();
	return api.revoke(Object.keys(session.tokens).map(tokenKey => ({
		type: session.tokens[tokenKey].type,
		value: session.tokens[tokenKey].value,
	})))
	.then(clearSession())
	.catch(() => {});
};
