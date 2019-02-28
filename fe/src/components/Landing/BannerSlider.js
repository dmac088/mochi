import React, { Component } from 'react';
import { connect } from 'react-redux';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/Helper';
const $ = window.$;


class BannerSlider extends Component {


next = () => {
  this.slider.slickNext();
}

previous = () => {
  this.slider.slickPrev();
}

render() {
  const settings = {
    arrows: true,
    autoplay: false,
    dots: false,
    infinite: true,
    slidesToShow: 4,
    prevArrow: <SlickArrowLeft />,
    nextArrow: <SlickArrowRight />,
    responsive: [{
      breakpoint: 1499,
      settings: {
        slidesToShow: 4,
      }
    },
    {
      breakpoint: 1199,
      settings: {
        slidesToShow: 4,
      }
    },
    {
      breakpoint: 991,
      settings: {
        slidesToShow: 2,
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
  return (
    <div className="banner-slider-container">
      <Slider ref={c => (this.slider = c)} {...settings}>
        <div className="gf-product banner-slider-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product01.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        <div className="gf-product banner-slider-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product02.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        <div className="gf-product  banner-slider-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product03.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        <div className="gf-product  banner-slider-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product04.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        <div className="gf-product  banner-slider-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product05.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
      </Slider>
    </div>
  )
}

}

export default BannerSlider;
