import * as actionTypes from './ActionTypes';


export const update = session => ({
	type: actionTypes.GET_SESSION,
	session,
});

export const updateSession = () => async dispatch => {

    const res = await axios.get('https://localhost:8090/oauth/token');

    console.log(res.data._embedded.categoryResources);

    dispatch({
      type: GET_SESSION,
      payload: res.data._embedded.categoryResources,
    });

}
