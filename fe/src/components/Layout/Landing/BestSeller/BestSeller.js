import React from 'react';
import Slider from "react-slick";
import Product from './Product';
import { SlickArrowLeft, SlickArrowRight } from '../HeroSlider/sliderHelper';
const $ = window.$;


const settings = {
  arrows: true,
  autoplay: false,
  dots: false,
  infinite: true,
  slidesToShow: 3,
  prevArrow: <SlickArrowLeft />,
  nextArrow: <SlickArrowRight />,
  responsive: [{
    breakpoint: 1499,
    settings: {
      slidesToShow: 3,
    }
  },
  {
    breakpoint: 1199,
    settings: {
      slidesToShow: 3,
    }
  },
  {
    breakpoint: 991,
    settings: {
      slidesToShow: 3,
    }
  },
  {
    breakpoint: 767,
    settings: {
      slidesToShow: 2,
    }
  },
  {
    breakpoint: 575,
    settings: {
      slidesToShow: 2,
    }
  },
  {
    breakpoint: 479,
    settings: {
      slidesToShow: 1,
    }
  }
  ]
};

function BestSeller() {
  let slider;
  return (
    <div className="slider best-seller-slider mb-35">
      <div className="container">
        <div className="row">
          <div className="col-lg-12">
            <div className="section-title">
              <h3>best seller</h3>
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-lg-12">
              <Slider className="best-seller-slider-container pt-15 pb-15" ref={c => (slider = c)} {...settings}>
                <div className="col">
                  <div className="single-best-seller-item">
                    <Product />
                    <Product />
                  </div>
                </div>
                <div className="col">
                  <div className="single-best-seller-item">
                    <Product />
                    <Product />
                  </div>
                </div>
                <div className="col">
                  <div className="single-best-seller-item">
                    <Product />
                    <Product />
                  </div>
                </div>
                <div className="col">
                  <div className="single-best-seller-item">
                    <Product />
                    <Product />
                  </div>
                </div>
                <div className="col">
                  <div className="single-best-seller-item">
                    <Product />
                    <Product />
                  </div>
                </div>
                <div className="col">
                  <div className="single-best-seller-item">
                    <Product />
                    <Product />
                  </div>
                </div>
              </Slider>
          </div>
        </div>
      </div>
    </div>
  )
}

export default BestSeller;