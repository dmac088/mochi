
import React from 'react';

function Address() {
    return (
        <React.Fragment>
            <h3>Billing Address</h3>

            <address>
                <p><strong>Alex Tuntuni</strong></p>
                <p>1355 Market St, Suite 900 <br />San Francisco, CA 94103</p>
                <p>Mobile: (123) 456-7890</p>
            </address>

            <a href="#" className="btn d-inline-block edit-address-btn"><i className="fa fa-edit"></i>Edit Address</a>
        </React.Fragment>
    );
}

export default Address;

