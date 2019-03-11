import React, { Component } from 'react';
import { connect } from 'react-redux';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/Helper';
const $ = window.$;


class BannerSliderProduct extends Component {

  render() {
    const { product } = this.props;
    return (
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
            <a href="shop-left-sidebar.html">{this.props.categoryDesc}</a>
          </div>
          <h3 className="product-title"><a href="single-product.html">{product.productDesc}</a></h3>
          <div className="price-box">
            <span className="main-price">$89.00</span>
            <span className="discounted-price">$80.00</span>
          </div>
        </div>
      </div>
    )
  }
}

export default BannerSliderProduct;
