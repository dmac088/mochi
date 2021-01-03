import React from "react";

function AddressEdit(props) {
    console.log(props);
    return (
        <React.Fragment>
            <div className="account-details-form">
                <form action="#">
                    <div className="row">
                        <div className="col-12 mb-30">
                            <input id="address-line-1" placeholder="Address Line 1" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="address-line-2" placeholder="Address Line 2" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="address-line-3" placeholder="Address Line 3" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="country" placeholder="Country" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="post-code" placeholder="Post Code" type="text" />
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