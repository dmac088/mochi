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
	console.log('dispatching event to redux store');
	store.dispatch(actionCreators.update(initialState));
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
	//injection of a function reference means the function behaves
	//as if it were nested within the parentheses
	.then(onRequestSuccess)
	.catch(onRequestFailed);


	const  onRequestSuccess = (response) => {
		 //we capture our response errors here and act accordingly
		 console.log('onRequestSuccess');
		 if (response.status === 400) {
			 console.log('Invalid username or password');
			 clearSession();
			 return;
		 };

		 //response is an object of type promise
		 //we call the text function to execute the promise
		 response.text()
		 .then((responseText) => {
		 	 return responseText;
		 })
		 .then(persistTokens)
		 .catch((e) => {
	 	 			console.log(e);
	   });
	};

	const  persistTokens = (body) => {
		const reformTokens = formatTokenResponse(
															JSON.parse(body).access_token,
															JSON.parse(body).refresh_token,
															JSON.parse(body).username,
															true,
															JSON.parse(body).expires_in
													);

		//transfrom flat structure to a nested one
		const tokens = 				reformTokens.tokens.reduce(
																												(prev, item) => ({
																														...prev,
																														[item.type]: item,
																												}),
																									{});

		//trigger a store update
		store.dispatch(actionCreators.update({ tokens, user: reformTokens.user }));

		//set the token timeout
		setSessionTimeout(tokens.access.expiresIn);
	}

export const revoke = () => {
	const session = selectors.get();
	return api.revoke(Object.keys(session.tokens).map(tokenKey => ({
		type: session.tokens[tokenKey].type,
		value: session.tokens[tokenKey].value,
	})))
	.then(clearSession())
	.catch(() => {});
};


const formatTokenResponse = (accessToken, refreshToken, user, authenticated, expires_in) => ({
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
		username: user,
		authenticated: authenticated,
	},
});



const onRequestFailed = (exception) => {
	clearSession();
	throw exception;
};
