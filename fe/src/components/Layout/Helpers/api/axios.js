import axios from "axios";
// import store from '../../../../store';
import LocalStorageService from "../Storage/LocalStorageService";
// import { getAuthPath } from '../../Helpers/Route/Route';
// import { useHistory, useParams } from "react-router-dom";

export const instance = axios.create({
    baseURL: '',
    headers: {
      "content-type": "application/json"
    },
    responseType: "json"
  });

// axios.interceptors.request.use(request => {
//     console.log('Starting Request', request)
//     return request
//   })
  
// axios.interceptors.response.use(response => {
//     console.log('Response:', response)
//     return response
//   })


// LocalstorageService
const localStorageService = LocalStorageService.getService();

//Add a request interceptor
axios.interceptors.request.use(
    config => {
        const token = localStorageService.getAccessToken();
        console.log(token);
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
// axios.interceptors.response.use((response) => {
//     return response
// }, function (error) {
//     const originalRequest = error.config;
//     let history = useHistory();
//     let { lang, curr } = useParams();
//     const match = { 
//         params: {
//             "lang": lang,
//             "curr": curr,
//         }
//     };
//     const state = store.getState();
//     const tokenLink = state.discovery.links.accessTokens.href;

//     console.log("interceptor 2");

//     if (error.response.status === 401 && originalRequest.url === tokenLink) {
//         history.push(getAuthPath(match));
//         return Promise.reject(error);
//     }

//     if (error.response.status === 401 && !originalRequest._retry) {

//         originalRequest._retry = true;
//         const refreshToken = localStorageService.getRefreshToken();
//         return axios.post(tokenLink,
//             {
//                 "refresh_token": refreshToken
//             })
//             .then(res => {
//                 if (res.status === 201) {
//                     localStorageService.setToken(res.data);
//                     axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorageService.getAccessToken();
//                     return axios(originalRequest);
//                 }
//             })
//     }
//     return Promise.reject(error);
// });