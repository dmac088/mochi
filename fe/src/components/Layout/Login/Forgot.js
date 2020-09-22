import React from 'react';


function Forgot(props) {

    const { match, history } = props;

    return (
        <div className="page-content mb-50">
            <div className="container">
                <div className="row">
                    <div className="col-sm-12 col-md-12 col-xs-12 col-lg-6 mb-30">
                        <form action="#">
                            <div className="login-form">
                                <h4 className="login-title">Reset Password</h4>
                                <div className="row">
                                    <div className="col-md-12 col-12 mb-20">
                                        <label>Email Address*</label>
                                        <input className="mb-0" type="email" placeholder="Email Address" />
                                    </div>
                                    <div className="col-md-12">
                                        <button className="register-button mt-0">Reset</button>
                                    </div>
                                    <div className="col-md-12">
                                      
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Forgot;