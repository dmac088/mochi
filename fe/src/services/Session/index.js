import { instance } from "../../components/Layout/Helpers/api/axios";
import { clearSession,
         getSessionStarted,
         getSessionSuccess,
         getSessionFailure } from '../../actions/SessionActions';
import axios from "axios";
import LocalStorageService from '../../components/Layout/Helpers/Storage/Token/LocalStorageService';
import * as apiConfig from '../api';

export const authenticate = (username, password) => {

    return (dispatch, getState) => {
      const state = getState();
      const localStorageService = LocalStorageService.getService();
  
      const { href } = state.discovery.links.accessTokens;
  
      const form = new FormData();
      Object.keys(apiConfig.formData).forEach((key) => {
        form.append(key, apiConfig.formData[key])
      });
  
      form.append('username', username);
      form.append('password', password);
  
      dispatch(getSessionStarted());
  
      instance.post(
        href,
        form,
        apiConfig.config,
      ).then((response) => {
        dispatch(getSessionSuccess(response.data));
        localStorageService.setToken(response.data);
      }).catch((error) => {
        dispatch(getSessionFailure(error.response));
      });
    }
  }
  
  export const reauthenticate = () => {
    return (dispatch) => {
  
      const localStorageService = LocalStorageService.getService();
      const tokenLink = 'https://localhost:8090/oauth/token';
      const refreshToken = localStorageService.getRefreshToken();
  
      const form = new FormData();
      form.append('refresh_token', refreshToken);
      form.append('grant_type', 'refresh_token');
  
      if (!refreshToken) {
        console.log("No refresh token found in localstorage");
        return;
      }
  
      return axios.post(
        tokenLink,
        form,
        apiConfig.config)
        .then(response => {
          if (response.status === 200) {
            console.log('assigning new access token to further requests.....');
            dispatch(refreshTokens(response.data));
            localStorageService.setToken(response.data);
            instance.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.access_token;
          }
        });
    }
  }
  
  export const refreshTokens = (data) => {
    return (dispatch) => {
      dispatch(getSessionSuccess(data));
    }
  }
  
  export const logoutSession = () => {
    return (dispatch) => {
      const localStorageService = LocalStorageService.getService();
      localStorageService.clearToken();
      dispatch(clearSession());
    }
  }