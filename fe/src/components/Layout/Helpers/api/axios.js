import axios from "axios";
import store from '../../../../store';
import LocalStorageService from "../Storage/Token/LocalStorageService";
import { getAccountPath } from '../../Helpers/Route/Route';
import { history } from '.././../Helpers/Route/History';
import { matchPath } from 'react-router'
import { refreshTokens, logoutSession } from '../../../../services/Session';
import * as apiConfig from '../../../../services/api';
import queryString from 'query-string';

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

    const match = matchPath(history.location.pathname, {
        path: "/:lang/:curr",
        exact: false,
        strict: true
    });

    url = url.replace('{locale}', match.params.lang);
    url = url.replace('{currency}', match.params.curr);

    const query = queryString.parse(history.location.search);
    url = url.replace('{page}', query.page || 0);
    url = url.replace('{size}', query.size || 10);
    url = url.replace('{sort}', query.sort || 'bestMatch');

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


// for multiple requests
let isRefreshing = false;
let failedQueue = [];

const processQueue = (error, token = null) => {
    failedQueue.forEach(prom => {
        if (error) {
            prom.reject(error);
        } else {
            prom.resolve(token);
        }
    })

    failedQueue = [];
}

//Add a response interceptor
instance.interceptors.response.use((response) => {
    return response
}, function (error) {

    let state = store.getState();

    const originalRequest = error.config;

    //mock a match object for our params
    const match = matchPath(history.location.pathname, {
        path: "/:lang/:curr",
        exact: true,
        strict: true
    });

    //get the token link
    const tokenLink = 'https://localhost:8090/oauth/token';

    //when we get 2 or more 401 at the same time, the a duplicate request with the same refresh token is fired
    //to the token endpoint, resulting in a 500 error
    if (error.response && error.response.status === 401 && !originalRequest._retry) {
        console.log('using refresh token to obtain new access token.....');

        originalRequest._retry = true;
    
        //if the token is refreshing then add to the failed queue
        if (isRefreshing) {
            return new Promise(function (resolve, reject) {
                failedQueue.push({ resolve, reject })
            }).then(() => {
                return instance(originalRequest);
            }).catch(err => {
                return Promise.reject(err);
            })
        }

        isRefreshing = true;

        const refreshToken = state.session.refresh_token;

        const form = new FormData();
        form.append('refresh_token', refreshToken);
        form.append('grant_type', 'refresh_token');

        if (!refreshToken) {
            console.log("No refresh token found in session state, redirecting to login...");
            history.push(getAccountPath(match));
            return Promise.reject(error);
        }

        return new Promise(function (resolve, reject) {
            axios.post(
                tokenLink,
                form,
                apiConfig.config)
                .then(response => {
                    if (response.status === 200) {
                        console.log('assigning new access token for further requests.....');
                        store.dispatch(refreshTokens(response.data));
                        localStorageService.setToken(response.data);
                        instance.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.access_token;
                        processQueue(null, response.data.access_token);
                        resolve(instance(originalRequest));
                    }
                })
                .catch((err) => {
                    console.log('not good! giving up! login again please!');
                    store.dispatch(logoutSession())
                    history.push(getAccountPath(match));
                    processQueue(err, null);
                    reject(err);
                })
                .finally(() => { isRefreshing = false })
        });
    }
    return Promise.reject(error);
});