import axios from "axios";
import store from '../../../../store';
import LocalStorageService from "../Storage/LocalStorageService";
import { getAccountPath } from '../../Helpers/Route/Route';
import { history, params, accountParams } from '.././../Helpers/Route/History';
import { refreshTokens, logoutSession } from '../../../../actions/SessionActions';
import * as apiConfig from '../../../../services/api'; 

export const instance = axios.create({
    baseURL: '',
    headers: {
        "content-type": "application/json"
    },
    // responseType: "json"
});

// LocalstorageService
const localStorageService = LocalStorageService.getService();

//Add a request interceptor
instance.interceptors.request.use(config => {
    
    const state = store.getState();
    let { url } = config;

    const match = {
        params: { ...params },
    };

    url = url.replace('{locale}', match.params.lang);
    url = url.replace('{currency}', match.params.curr);
    //firstly try to retrieve the token from the file system, then try redux
    const token = state.session.access_token;

    //if an access token exists we should use it
    if (token) {
        config.headers['Authorization'] = 'Bearer ' + token;
    }
    // config.headers['Content-Type'] = 'application/json';
    return {
        ...config,
        url: url,
    };
},
    error => {
        Promise.reject(error)
    });

//Add a response interceptor
instance.interceptors.response.use((response) => {
    return response
}, function (error, dispatch) {

    let state = store.getState();

    const originalRequest = error.config;

    //mock a match object for our params
    const match = {
        params: { ...params },
    };

    //get the token link
    const tokenLink = 'https://localhost:8090/oauth/token';

    //when we get 2 or more 401 at the same time, the a duplicate request with the same refresh token is fired
    //to the token endpoint, resulting in a 500 error
    if (error.response && error.response.status === 401 && !originalRequest._retry) {
        console.log('using refresh token to obtain new access token.....');

        originalRequest._retry = true;
        const refreshToken = state.session.refresh_token;

        const form = new FormData();
        form.append('refresh_token', refreshToken);
        form.append('grant_type', 'refresh_token');

        if(!refreshToken) {
            console.log("No refresh token found in session state, redirecting to login...");
            dispatch(logoutSession())
            .then(() => {
                history.push(getAccountPath(match));
            });
            return Promise.reject(error);
        }

        return axios.post(
            tokenLink,
            form,
            apiConfig.config,)
            .then(response => {
                if (response.status === 200) {
                    console.log('assigning new access token to further requests.....');
                    store.dispatch(refreshTokens(response.data));
                    localStorageService.setToken(response.data);
                    instance.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.access_token;
                    return instance(originalRequest);
                }
            })
    }
    return Promise.reject(error);

});