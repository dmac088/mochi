
import store from '../../store';
import * as session from '../session';
import * as api from './api';
import * as actionCreators from './actions';
import * as productActionCreators from '../product/actions';
import { initialState } from './reducer';

	export const findAll = (locale) =>
		api.findAll(locale)
		.then((response) => {

      return response.text();
    })
    .then((responseText)=> {
      return JSON.parse(responseText);
    })
    .then((responseJSON)=> {
      store.dispatch(productActionCreators.update({"product": responseJSON}));
    })
    .catch((err) => {
      console.log(err);
    })
		.then(onRequestSuccess)
		.catch(onRequestFailed);

	const onRequestSuccess = (response) => {
		console.log('request successfully completed!');
	};

	const onRequestFailed = (exception) => {
		console.log('request failed!');
		session.clearSession();
		throw exception;
	};
