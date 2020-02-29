import React from 'react';
import { connect } from 'react-redux';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/uiHelper';
const $ = window.$;

const settings = {
  arrows: true,
  autoplay: true,
  dots: false,
  infinite: true,
  slidesToShow: 5,
  prevArrow: <SlickArrowLeft />,
  nextArrow: <SlickArrowRight />,
  responsive: [{
    breakpoint: 1499,
    settings: {
      slidesToShow: 5,
    }
  },
  {
    breakpoint: 1199,
    settings: {
      slidesToShow: 5,
    }
  },
  {
    breakpoint: 991,
    settings: {
      slidesToShow: 4,
    }
  },
  {
    breakpoint: 767,
    settings: {
      slidesToShow: 3,
    }
  },
  {
    breakpoint: 575,
    settings: {
      slidesToShow: 2,
    }
  }
  ]
};

export const BrandSlider = (props) => {
    let slider;
    return (
      <div className="slider brand-logo-slider mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="section-title">
                <h3>brand logos</h3>
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col-lg-12">
                <Slider className="brand-logo-wrapper pt-20 pb-20" ref={c => (slider = c)} {...settings}>
                  <div className="col">
                    <div className="single-brand-logo">
                      <a href="#">
                        <img src="assets/images/brands/brand1.png" className="img-fluid" alt="" />
                      </a>
                    </div>
                  </div>
                  <div className="col">
                    <div className="single-brand-logo">
                      <a href="#">
                        <img src="assets/images/brands/brand2.png" className="img-fluid" alt="" />
                      </a>
                    </div>
                  </div>
                  <div className="col">
                    <div className="single-brand-logo">
                      <a href="#">
                        <img src="assets/images/brands/brand3.png" className="img-fluid" alt="" />
                      </a>
                    </div>
                  </div>
                  <div className="col">
                    <div className="single-brand-logo">
                      <a href="#">
                        <img src="assets/images/brands/brand4.png" className="img-fluid" alt="" />
                      </a>
                    </div>
                  </div>
                  <div className="col">
                    <div className="single-brand-logo">
                      <a href="#">
                        <img src="assets/images/brands/brand5.png" className="img-fluid" alt="" />
                      </a>
                    </div>
                  </div>
                  <div className="col">
                    <div className="single-brand-logo">
                      <a href="#">
                        <img src="assets/images/brands/brand6.png" className="img-fluid" alt="" />
                      </a>
                    </div>
                  </div>
                </Slider>
            </div>
          </div>
        </div>
      </div>
    );
  }
