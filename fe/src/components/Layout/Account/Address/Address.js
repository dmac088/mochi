import React, { useEffect, useState } from "react";
import { instance as axios } from "../../../Layout/Helpers/api/axios";

function Address(props) {
    const { customer } = props;

    const [stateObject, setObjectState] = useState({
        address: null,
        loading: false,
    });

    useEffect(() => {
        let isSubscribed = true;
        if(!customer.loading && customer.isDone) {
            axios.get(customer._links.address.href)
            .then((response) => {
                if(isSubscribed) {
                    console.log(response.data);
                    setObjectState((prevState) => ({
                        ...prevState,
                        address: response.data.data,
                        loading: false,
                    }));
                }
            });
        }
        return () => (isSubscribed = false);
    }, [customer.loading, customer.isDone]);

    if(!stateObject.address) {
        return null;
    }

    return (
        <React.Fragment>
            <h3>Default Billing Address</h3>

            <address>
                <p><strong>{customer.data.givenName}</strong></p>
                <p>{stateObject.address.addressLine1} 
                    <br />{stateObject.address.addressLine2}
                    <br />{stateObject.address.addressLine3}
                </p>
                <p>{stateObject.address.country}</p>
                <p>{stateObject.address.postCode}</p>
            
            <a href="#" className="btn d-inline-block edit-address-btn"><i className="fa fa-edit"></i>Edit Address</a>
            </address>

            <h3>Default Shipping Address</h3>

            <address>
                <p><strong>Alex Tuntuni</strong></p>
                <p>1355 Market St, Suite 900 <br />San Francisco, CA 94103</p>
                <p>Mobile: (123) 456-7890</p>
            
            <a href="#" className="btn d-inline-block edit-address-btn"><i className="fa fa-edit"></i>Edit Address</a>
            </address>
        </React.Fragment>
    );
}

export default Address;

