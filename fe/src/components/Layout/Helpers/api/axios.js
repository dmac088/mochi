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

// instance.interceptors.request.use(request => { 
//     console.log('Starting Request', request)
//     return request
//   })
  
//   instance.interceptors.response.use(response => {
//     console.log('Response:', response)
//     return response
//   })


// // LocalstorageService
const localStorageService = LocalStorageService.getService();

//Add a request interceptor
instance.interceptors.request.use(
    config => {

        const state = store.getState();
        console.log(state);

        //firstly try to retrieve the token from redux
        const token = (!state.session) 
        ? localStorageService.getAccessToken()
        : state.session.access_token

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

// //Add a response interceptor
instance.interceptors.response.use((response) => {
    return response
}, function (error) {

    

    const originalRequest = error.config;
    if(!error.response) { return originalRequest; }

    //get the parameters of locale
    //mock a match object for our params
    const match = { 
        params: {...params},
    };

    //get the token link
    const state = store.getState();
    const tokenLink = state.discovery.links.accessTokens.href;

    //if we get a 401 error response from the server then we need to login again
    if (error.response.status === 401 && originalRequest.url === tokenLink) {
        history.push(getAuthPath(match));   
        return Promise.reject(error);
    }

    if (error.response.status === 401 && !originalRequest._retry) {

        originalRequest._retry = true;
        const refreshToken = localStorageService.getRefreshToken();
        return axios.post(tokenLink,
            {
                "refresh_token": refreshToken
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