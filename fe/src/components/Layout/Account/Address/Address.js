import React, { useEffect } from "react";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';
import { getAccountSubPath } from "../../Helpers/Route/Route";
import { Link } from 'react-router-dom'

import { useDispatch, useSelector } from 'react-redux';

function Address(props) {
    const { match, type, address, getAddress, updateAddress } = props;
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
            : <React.Fragment>
                <h3>Default {address.data.addressTypeDesc}</h3>

                <address>
                    <p><strong>{customer.data.givenName} {customer.data.familyName}</strong></p>
                    <p>{address.data.addressLine1}
                        <br />{address.data.addressLine2}
                        <br />{address.data.addressLine3}
                    </p>
                    <p>{address.data.country}</p>
                    <p>{address.data.postCode}</p>

                    <Link   to={() => getAccountSubPath(match, 'editaddress')} 
                            className="btn d-inline-block edit-address-btn">
                            <i className="fa fa-edit"></i>Edit Address</Link>
                </address>

            </React.Fragment>
    );
}

export default Address;

