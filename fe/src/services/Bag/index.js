import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import {
    getBagStarted,
    getBagSuccess,
    getBagFailure,
} from '../../actions/BagActions';

import {
    getBagContentsStarted,
    getBagContentsSuccess,
    getBagContentsFailure,
    addBagItemStarted,
    addBagItemSuccess,
    addBagItemFailure,
    removeBagItemStarted,
    removeBagItemSuccess,
    removeBagItemFailure,
} from '../../actions/BagContentsActions';

export const addItem = (item) => {
    console.log('addItem');
    return (dispatch, getState) => {

        dispatch(addBagItemStarted()); 

        return axios.post(getState().bag.links.addItem.href,item)
                    .then(() => {
                        dispatch(addBagItemSuccess());
                    })
                    .then(() => {
                        dispatch(getBag());
                    })
                    .catch(() => {
                        dispatch(addBagItemFailure()); 
                    });
    }
}

export const removeItem = (itemCode) => {
    return (dispatch, getState) => {

        dispatch(removeBagItemStarted()); 

        return axios.get(getState().bag.links.removeItem.href.replace('{itemCode}', itemCode))
                    .then(() => {
                        dispatch(removeBagItemSuccess());
                        dispatch(getBag());
                    })
                    .catch(() => {
                        dispatch(removeBagItemFailure()); 
                    });
    }
}
    
export const getBag = () => {
    return (dispatch, getState) => {
        const state = getState();
        
        dispatch(getBagStarted());  

        return axios.get(state.discovery.links.getBag.href)
        .then((payload) => {
             return payload.data;
        }).then((bag) => {
             dispatch(getBagSuccess(bag));
        }).catch((error) => {
             dispatch(getBagFailure(error.response));
        });
    }
}

//we need to inject the dependencies into the function
export const getBagContents = () => {
    console.log('getBagContents');
    return (dispatch, getState) => {

        dispatch(getBagContentsStarted());  

        return axios.get(getState().bag.links.bagContents.href)
        .then((payload) => {
            return (payload.data._embedded)
            ? payload.data._embedded.bagItemResources
            : [];
        }).then((items) => {
            dispatch(getBagContentsSuccess(items));
        }).catch((error) => {
            console.log(error);
            dispatch(getBagContentsFailure(error.response));
        });
    }
}



