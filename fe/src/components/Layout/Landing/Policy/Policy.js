import React from 'react';
const $ = window.$;

  export const Policy = (props) => {
    return (
      <div className="policy-section mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="policy-titles d-flex align-items-center flex-wrap">
                <div className="single-policy">
                  <span><img src="assets/images/policy-icon1.png" className="img-fluid" alt="" /></span>
                  <p> FREE SHIPPING ON ORDERS OVER $200</p>
                </div>
                <div className="single-policy">
                  <span><img src="assets/images/policy-icon2.png" className="img-fluid" alt="" /></span>
                  <p>30 -DAY RETURNS MONEY BACK</p>
                </div>

                <div className="single-policy">
                  <span><img src="assets/images/policy-icon3.png" className="img-fluid" alt="" /></span>
                  <p> 24/7 SUPPORT</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

export default Policy;