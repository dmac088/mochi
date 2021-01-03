import React, { useEffect, useState } from "react";
import { instance as axios } from "../../../Layout/Helpers/api/axios";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';
import { getAccountSubPath } from "../../Helpers/Route/Route";
import { Link } from 'react-router-dom';

function Address(props) {
    const { match, history, customer } = props;

    const [stateObject, setObjectState] = useState({
        address: null,
        loading: true,
        isDone: false,
    });

    useEffect(() => {
        let isSubscribed = true;
        if (!customer.loading && customer.isDone) {
            axios.get(customer._links.address.href)
                .then((response) => {
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
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
        (stateObject.loading)
            ? <Spinner />
            : <React.Fragment>
                <h3>Default Billing Address</h3>

                <address>
                    <p><strong>{customer.data.givenName}</strong></p>
                    <p>{stateObject.address.addressLine1}
                        <br />{stateObject.address.addressLine2}
                        <br />{stateObject.address.addressLine3}
                    </p>
                    <p>{stateObject.address.country}</p>
                    <p>{stateObject.address.postCode}</p>

                    <Link   to={() => getAccountSubPath(match, 'editaddress')} 
                            className="btn d-inline-block edit-address-btn">
                            <i className="fa fa-edit"></i>Edit Address</Link>
                </address>

                <h3>Default Shipping Address</h3>

                <address>
                    <p><strong>Alex Tuntuni</strong></p>
                    <p>1355 Market St, Suite 900 <br />San Francisco, CA 94103</p>
                    <p>Mobile: (123) 456-7890</p>

                    <Link to={() => getAccountSubPath(match, 'editaddress')} className="btn d-inline-block edit-address-btn"><i className="fa fa-edit"></i>Edit Address</Link>
                </address>
            </React.Fragment>
    );
}

export default Address;

