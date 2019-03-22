import React, { Component } from 'react';
import { connect } from 'react-redux';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/Helper';
import * as cartSelector from '../../services/cart/selectors';
import * as cartService from '../../services/cart';
import * as productApi from '../../data/products/api';
const $ = window.$;


class BannerSliderProduct extends Component {

  constructor(props){
    super(props);
    this.state = {
      "product": null,
    }
  }

  componentDidMount() {
    const { product } = this.props;
    this.setState({
      "product": product,
    });
  }

  render() {
    const { product } = this.state;
    const { setCurrentProductId } = this.props;
    if(product === null) {return null;}
    return (
        <div key={product.productId} className="gf-product banner-slider-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src={product.productImage} className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a id={product.productId} onClick={setCurrentProductId} href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container">
                <span id={product.productId} className="icon_search" />
              </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">{this.props.categoryDesc}</a>
            </div>
            <h3 className="product-title">
              <a href="single-product.html">{product.productDesc}</a></h3>
            <div className="price-box">
              <span className="main-price">${product.productRrp}</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
    )
  }
}

export default BannerSliderProduct;
