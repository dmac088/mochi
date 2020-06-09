import axios from "axios";
import store from '../../../../store';
import LocalStorageService from "../Storage/LocalStorageService";
import { getAccountPath } from '../../Helpers/Route/Route';
import { history, params } from '.././../Helpers/Route/History';
import { refreshTokens } from '../../../../actions/SessionActions';
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

    //firstly try to retrieve the token from the file system, then try redux
    const token = state.session.access_token;

    console.log('using access token = ' + token);
    //if an access token exists we should use it
    if (token) {
        config.headers['Authorization'] = 'Bearer ' + token;
    }
    // config.headers['Content-Type'] = 'application/json';
    return config;
},
    error => {
        Promise.reject(error)
    });

//Add a response interceptor
instance.interceptors.response.use((response) => {
    return response
}, function (error) {

    let state = store.getState();

    const originalRequest = error.config;

    //get the parameters of locale
    //mock a match object for our params
    const match = {
        params: { ...params },
    };

    //get the token link
    const tokenLink = 'https://localhost:8090/oauth/token';

    //if we get a 401 error response from the server then we need to login again
    console.log(originalRequest.url + ' === ' + tokenLink);
    if (error.response.status === 401 && originalRequest.url === tokenLink) {
        history.push(getAccountPath(match));
        return Promise.reject(error);
    }

    if (error.response.status === 401 && !originalRequest._retry) {

        originalRequest._retry = true;
        const refreshToken = state.session.refresh_token;

        console.log('using http link: ' + tokenLink);
        console.log('using refresh token: ' + refreshToken);

        const form = new FormData();
        form.append('refresh_token', refreshToken);
        form.append('grant_type', 'refresh_token');

        return axios.post(
            tokenLink,
            form,
            {'Authorization': 'Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLXdyaXRlLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtd3JpdGUtY2xpZW50LXBhc3N3b3JkMTIzNA=='}
            )
            .then(response => {
                console.log(response);
                if (response.status === 201) {
                    store.dispatch(refreshTokens(response.data));
                    console.log('using new access token: ' + response.data.access_token);
                    axios.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.access_token;
                    return axios(originalRequest);
                }
            })
    }
    return Promise.reject(error);

});