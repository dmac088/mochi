import React, { Component } from 'react';
import { BannerSlider } from '../BannerSlider/BannerSlider';

export const PreviewCategory = (props) => {
    const { category } = props;
    return (
        <div className="slider slider-with-banner mb-35">
          <div className="container">
            <div className="row">
              <div className="col-lg-12">
                <div className="section-title">
                  <h3>{category.facetDisplayValue}</h3>
                </div>
              </div>
            </div>
            <div className="row">
              <div className="col-lg-12">
                <div className="banner-slider-wrapper">
                  <div className="row no-gutters">
                    <div className="col-lg-4 col-md-4 col-sm-12">
                      <div className="slider-side-banner">
                        <a href="shop-left-sidebar.html">
                          <img src="assets/images/banners/home4-category1-sidebar.jpg" className="img-fluid" alt="" />
                        </a>
                      </div>
                    </div>
                    <div className="col-lg-8 col-md-8 col-sm-12">
                      <BannerSlider
                        {...props}
                      />
                      <div className="row no-gutters">
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category1-banner1.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                        </div>
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category1-banner2.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    )
  }
