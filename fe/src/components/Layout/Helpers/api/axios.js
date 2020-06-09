import axios from "axios";
import store from '../../../../store';
import LocalStorageService from "../Storage/LocalStorageService";
import { getAuthPath } from '../../Helpers/Route/Route';
import { history, params } from '.././../Helpers/Route/History';

export const instance = axios.create({
    baseURL: '',
    headers: {
      "content-type": "application/json"
    },
    responseType: "json"
});

// LocalstorageService
const localStorageService = LocalStorageService.getService();

//Add a request interceptor
instance.interceptors.request.use(
    config => {

        const state = store.getState();

        //firstly try to retrieve the token from the file system, then try redux
        const token = (!state.session) 
        ? localStorageService.getAccessToken()
        : state.session.access_token

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

    const originalRequest = error.config;

    //get the parameters of locale
    //mock a match object for our params
    const match = { 
        params: {...params},
    };

    //get the token link
    const state = store.getState();
    const tokenLink = state.discovery.links.accessTokens.href;


    console.log(originalRequest.url );
    //if we get a 401 error response from the server then we need to login again
    if (error.response.status === 401 && originalRequest.url === tokenLink) {
        console.log('route to login page');
        history.push(getAuthPath(match));   
        return Promise.reject(error);
    }

    console.log('status = ' + error.response.status);
    console.log('retry = ' + originalRequest._retry);
    if (error.response.status === 401 && !originalRequest._retry) {

        originalRequest._retry = true;
        const refreshToken = (!state.session) 
                            ? localStorageService.getRefreshToken()
                            : state.session.refresh_token

        console.log('using refresh token = ' + refreshToken);
        console.log('refresh token linke = ' + tokenLink);
        return axios.post(tokenLink,
            {
                "refresh_token": refreshToken,
                "grant_type": "refresh_token",
            })
            .then(res => {
                if (res.status === 201) {
                    localStorageService.setToken(res.data);
                    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorageService.getAccessToken();
                    return axios(originalRequest);
                }
            })
    }
    return Promise.reject(error);

});