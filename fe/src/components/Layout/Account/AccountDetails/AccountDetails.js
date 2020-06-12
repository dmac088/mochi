import React from 'react';


function AccountDetails(props) {
    const { customer } = props;
    return (
        <React.Fragment>
            <h3>Account Details</h3>

            <div className="account-details-form">
                <form action="#">
                    <div className="row">
                        <div className="col-lg-6 col-12 mb-30">
                            <input id="first-name" placeholder={customer.givenName} type="text" />
                        </div>

                        <div className="col-lg-6 col-12 mb-30">
                            <input id="last-name" placeholder={customer.familyName} type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="display-name" placeholder="Display Name" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="email" placeholder="Email Address" type="email" />
                        </div>

                        <div className="col-12 mb-30"><h4>Password change</h4></div>

                        <div className="col-12 mb-30">
                            <input id="current-pwd" placeholder="Current Password" type="password" />
                        </div>

                        <div className="col-lg-6 col-12 mb-30">
                            <input id="new-pwd" placeholder="New Password" type="password" />
                        </div>

                        <div className="col-lg-6 col-12 mb-30">
                            <input id="confirm-pwd" placeholder="Confirm Password" type="password" />
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


export default AccountDetails;