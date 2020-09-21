import { instance as axios } from "../../components/Layout/Helpers/api/axios";
import { history } from '../../components/Layout/Helpers/Route/History';
import { getAccountPath } from "../../components/Layout/Helpers/Route/Route";
import { matchPath } from 'react-router';
import store from '../../store';
import {
    getBagStarted,
    getBagSuccess,
    getBagFailure,
    emptyBag
} from '../../actions/BagActions';
import {
    emptyBagContents,
} from '../../actions/BagContentsActions';

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


export const addToBag = (e) => {
    console.log('addToBag');
    e.preventDefault();

    const authenticated = store.getState().session.authenticated;

    if (!authenticated) {
        const match = matchPath(history.location.pathname, {
            path: "/:lang/:curr",
            exact: false,
            strict: true
        });
        history.push(getAccountPath(match));
        return;
    }

    store.dispatch(addItem({
        "itemUPC": e.target.id,
        "itemQty": 1,
    }));

}

const addItem = (item) => {
    console.log('addItem');
    return (dispatch, getState) => {

        dispatch(addBagItemStarted());

        return axios.post(getState().bag.links.addItem.href, item)
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
    console.log('removeItem');
    return (dispatch, getState) => {

        dispatch(removeBagItemStarted());

        return axios.get(getState().bag.links.removeItem.href.replace('{itemCode}', itemCode))
            .then(() => {
                dispatch(removeBagItemSuccess());
            })
            .then(() => {
                dispatch(getBag());
            })
            .catch(() => {
                dispatch(removeBagItemFailure());
            });
    }
}

export const getBag = () => {
    console.log('getBag');
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

export const clearBag = () => {
    console.log('clearBag');
    return (dispatch) => {
        dispatch(emptyBag());
        dispatch(emptyBagContents());
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



