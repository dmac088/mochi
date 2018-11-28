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

export const clearSession = () => {
	clearTimeout(sessionTimeout);

	//set the state back to initial state
	console.log('dispatching event to redux store');
	store.dispatch(actionCreators.update(initialState));
};

export const refreshToken = () => {
	console.log("called refresh token");
	const session = selectors.get();
	console.log("refres_token = " + JSON.parse(session.tokens).refresh_token);

	if (!session.tokens.refresh_token || !session.username) {
		return Promise.reject();
	}

	return api.refresh(session.tokens.refresh_token)
	.then(onRequestSuccess)
	.catch(onRequestFailed);
};

	export const authenticate = (userName, password) =>
		api.authenticate(userName, password)
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
			// console.log(responseText);
		 	 return JSON.parse(responseText);
		 })
		 .then((responseJSON) => {
			 	responseJSON.authenticated = true;
				return responseJSON;
		 })
		 .then(persistTokens)
		 .catch((e) => {
	 	 			console.log(e);
	   });
	};

	const  persistTokens = (tokens) => {
		console.log("tokens....");
		console.log(tokens);
		//trigger a store update

		store.dispatch(actionCreators.update({ tokens, "tokens": tokens }));

		//set the token timeout
		setSessionTimeout(tokens.expires_in);

		const session = selectors.get();
		console.log("session = " + session.tokens.authenticated);
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



const onRequestFailed = (exception) => {
	clearSession();
	throw exception;
};
