import store from '../../store';

export const get = () => store.getState().services.customer.customer;
