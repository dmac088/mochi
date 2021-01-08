import React from "react";
import Address from "../Address/Address";

function AddressList(props) {

    return (
        <React.Fragment>
            {/* billing address */}
            <Address 
            {...props}
            type="BIL01"/>
            <br />
            {/* mailing address */}
            <Address 
            {...props}
            type="MAI01"/>
        </React.Fragment>
    );

}

export default AddressList; 