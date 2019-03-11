import React, { Component } from 'react';
const $ = window.$;

class HighlightedProduct extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="gf-product tab-slider-sub-product">
        <div className="image">
          <a href="single-product.html">
            <span className="onsale">Sale!</span>
            <img src="assets/images/products/product01.jpg" className="img-fluid" alt="" />
          </a>
          <div className="product-hover-icons">
            <a className="active" href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
            <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
            <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
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
    )
  }
}
