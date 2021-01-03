import React, { useEffect } from "react";
import { instance as axios } from "../../../Layout/Helpers/api/axios";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';
import { getAccountSubPath } from "../../Helpers/Route/Route";
import { Link } from 'react-router-dom';

function Address(props) {
    const { match, customer, setAddressState, addressState } = props;

    useEffect(() => {
        let isSubscribed = true;
        if (!customer.loading && customer.isDone) {
            axios.get(customer._links.address.href)
                .then((response) => {
                    if (isSubscribed) {
                        setAddressState((prevState) => ({
                            ...prevState,
                            address: response.data.data,
                            loading: false,
                            isDone: true,
                        }));
                    }
                });
        }
        return () => (isSubscribed = false);
    }, [customer.loading, customer.isDone]);

    return (
        (addressState.loading)
            ? <Spinner />
            : <React.Fragment>
                <h3>Default Billing Address</h3>

                <address>
                    <p><strong>{customer.data.givenName}</strong></p>
                    <p>{addressState.address.addressLine1}
                        <br />{addressState.address.addressLine2}
                        <br />{addressState.address.addressLine3}
                    </p>
                    <p>{addressState.address.country}</p>
                    <p>{addressState.address.postCode}</p>

                    <Link   to={() => getAccountSubPath(match, 'editaddress')} 
                            className="btn d-inline-block edit-address-btn">
                            <i className="fa fa-edit"></i>Edit Address</Link>
                </address>
                
            </React.Fragment>
    );
}

export default Address;

