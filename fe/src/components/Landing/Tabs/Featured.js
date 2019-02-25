import React, { Component } from 'react';
import { connect } from 'react-redux';
import Slider from "react-slick";
const $ = window.$;

class Featured extends Component {


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
      slidesToShow: 4
    };
    return(
        <div className="tab-slider-container">
          <button onClick={this.previous} type="button" className="slick-arrow slick-prev"><i className="fa fa-caret-left"></i></button>
          <button onClick={this.next} type="button" className="slick-arrow slick-next"><i className="fa fa-caret-right"></i></button>
          <Slider ref={c => (this.slider = c)} {...settings}>
            <div className="single-tab-slider-item">
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
              <div className="gf-product tab-slider-sub-product">
                <div className="image">
                  <a href="single-product.html">
                    <img src="assets/images/products/product02.jpg" className="img-fluid" alt="" />
                  </a>
                  <div className="product-hover-icons">
                    <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
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
                    <span className="discounted-price">$80.00</span>
                  </div>
                </div>
              </div>
            </div>
            <div className="single-tab-slider-item">
              <div className="gf-product tab-slider-sub-product">
                <div className="image">
                  <a href="single-product.html">
                    <img src="assets/images/products/product03.jpg" className="img-fluid" alt="" />
                  </a>
                  <div className="product-hover-icons">
                    <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
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
                    <span className="discounted-price">$80.00</span>
                  </div>
                </div>
              </div>
              <div className="gf-product tab-slider-sub-product">
                <div className="image">
                  <a href="single-product.html">
                    <span className="onsale">Sale!</span>
                    <img src="assets/images/products/product04.jpg" className="img-fluid" alt="" />
                  </a>
                  <div className="product-hover-icons">
                    <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
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
            </div>
            <div className="single-tab-slider-item">
              <div className="gf-product tab-slider-sub-product">
                <div className="image">
                  <a href="single-product.html">
                    <span className="onsale">Sale!</span>
                    <img src="assets/images/products/product05.jpg" className="img-fluid" alt="" />
                  </a>
                  <div className="product-hover-icons">
                    <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
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
              <div className="gf-product tab-slider-sub-product">
                <div className="image">
                  <a href="single-product.html">
                    <img src="assets/images/products/product06.jpg" className="img-fluid" alt="" />
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
                    <span className="discounted-price">$80.00</span>
                  </div>
                </div>
              </div>
            </div>
            <div className="single-tab-slider-item">
              <div className="gf-product tab-slider-sub-product">
                <div className="image">
                  <a href="single-product.html">
                    <span className="onsale">Sale!</span>
                    <img src="assets/images/products/product07.jpg" className="img-fluid" alt="" />
                  </a>
                  <div className="product-hover-icons">
                    <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
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
              <div className="gf-product tab-slider-sub-product">
                <div className="image">
                  <a href="single-product.html">
                    <span className="onsale">Sale!</span>
                    <img src="assets/images/products/product08.jpg" className="img-fluid" alt="" />
                  </a>
                  <div className="product-hover-icons">
                    <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
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
            </div>
            <div className="single-tab-slider-item">
              <div className="gf-product tab-slider-sub-product">
                <div className="image">
                  <a href="single-product.html">
                    <span className="onsale">Sale!</span>
                    <img src="assets/images/products/product09.jpg" className="img-fluid" alt="" />
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
              <div className="gf-product tab-slider-sub-product">
                <div className="image">
                  <a href="single-product.html">
                    <span className="onsale">Sale!</span>
                    <img src="assets/images/products/product10.jpg" className="img-fluid" alt="" />
                  </a>
                  <div className="product-hover-icons">
                    <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
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
            </div>
          </Slider>
        </div>
    )
  }
}

export default Featured;
