


export const register = (username, password) => dispatch => {  
    return discoveryService.discoverAll()
      .then((response) => {
        const form = new FormData();
        Object.keys(apiConfig.formData).forEach((key) => {
          form.append(key, apiConfig.formData[key])
        });
  
        form.append('username', username);
        form.append('password', password);
  
        axios.post(
          response.data._links.accessTokens.href,
          form,
          apiConfig.config,
          ).then((payload) => {
            return payload.data;
          }).then((tokens) => {
            dispatch({
              type: GET_SESSION,
              payload: tokens,
            });
          }).catch((error)=> {
            dispatch({
              type: GET_ERROR,
              payload: error.response,
            });
        });
      });
  }