import React from "react";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';

function AddressEdit(props) {
    const { addressState } = props;

    if(!addressState.address) {
        return null;
    }
    return (
        (!addressState.address)
        ? <Spinner />
        : <React.Fragment>
            <h3>Edit Billing Address</h3>
            <div className="account-details-form">
                <form action="#">
                    <div className="row">
                        <div className="col-12 mb-30">
                            <input id="address-line-1" placeholder={addressState.address.addressLine1} type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="address-line-2" placeholder={addressState.address.addressLine2} type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="address-line-3" placeholder={addressState.address.addressLine3} type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="country" placeholder={addressState.address.country} type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="post-code" placeholder={addressState.address.postCode} type="text" />
                        </div>

                        <div className="col-12">
                            <button className="save-change-btn">Save Changes</button>
                        </div>

                    </div>
                </form>
            </div>
        </React.Fragment>
    );
}

export default AddressEdit;