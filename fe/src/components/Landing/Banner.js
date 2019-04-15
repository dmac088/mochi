import React from 'react';
import { connect } from 'react-redux';
const $ = window.$;

  export const Banner = (props) => {
    return (
      <div className="double-banner-section mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-6 col-md-6 col-sm-12 mb-xs-35">
              <div className="single-banner">
                <a href="shop-left-sidebar.html">
                  <img src="assets/images/banners/home2-banner1-1.jpg" className="img-fluid" alt="" />
                </a>
              </div>
            </div>
            <div className="col-lg-6 col-md-6 col-sm-12">
              <div className="single-banner">
                <a href="shop-left-sidebar.html">
                  <img src="assets/images/banners/home2-banner1-2.jpg" className="img-fluid" alt="" />
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
