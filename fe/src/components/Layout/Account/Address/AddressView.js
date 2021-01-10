import React from "react";

function AddressView(props) {
    const { address, customer, toggleEdit } = props;

    return (
        <React.Fragment>
            <h3>Default {address.data.addressTypeDesc}</h3>

            <address>
                <p><strong>{customer.data.givenName} {customer.data.familyName}</strong></p>
                <p>{address.data.addressLine1}
                    <br />{address.data.addressLine2}
                    <br />{address.data.addressLine3}
                </p>
                <p>{address.data.country}</p>
                <p>{address.data.postCode}</p>
                <a onClick={toggleEdit}
                    className="btn d-inline-block edit-address-btn">
                    <i className="fa fa-edit"></i>Edit Address</a>
            </address>
        </React.Fragment>
    );
}

export default AddressView;

