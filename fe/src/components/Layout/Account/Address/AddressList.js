import React from "react";
import Address from "../Address/Address";
import { getAddress as getBillingAddress,
         updateAddress as updateBillingAddress } from '../../../../services/BillingAddress/index';
import { getAddress as getShippingAddress,
         updateAddress as updateShippingAddress } from '../../../../services/ShippingAddress/index';
import { useSelector } from 'react-redux';

function AddressList(props) {

    const billingAddress = useSelector(state => state.billingAddress);
    const shippingAddress = useSelector(state => state.shippingAddress);

    return (
        <React.Fragment>
            {/* billing address */}
            <Address 
            {...props}
            address = {billingAddress}
            getAddress = {getBillingAddress}
            updateAddress = {updateBillingAddress}
            type="BIL01"/>
            <br />
            {/* mailing address */}
            <Address 
            {...props}
            address = {shippingAddress}
            getAddress = {getShippingAddress}
            updateAddress = {updateShippingAddress}
            type="MAI01"/>
        </React.Fragment>
    );

}

export default AddressList; 