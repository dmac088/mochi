import React, { useEffect } from "react";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';
import { useDispatch, useSelector } from 'react-redux';
import AddressView from "./AddressView";

function Address(props) {
    const {type, address, getAddress} = props;
    const dispatch = useDispatch();
    const customer = useSelector(state => state.customer);

    useEffect(() => {
        let isSubscribed = true;
        if(isSubscribed) {
            if (!customer.loading && customer.isDone) {
                dispatch(getAddress(customer, type));
            }
        }
        return () => (isSubscribed = false);
    }, [customer.loading, customer.isDone]);

    return (
        ((!address.isDone || address.loading))
            ? <Spinner />
            : <AddressView  
                address={address}
                customer={customer} />
    );
}

export default Address;

