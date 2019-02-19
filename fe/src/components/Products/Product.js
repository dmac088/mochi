import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class Product extends Component {

  render() {
    return (
      <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
          <div className="gf-product shop-grid-view-product">
            <div className="image">
              <a href="single-product.html">
                <span className="onsale">Sale!</span>
                <img src="assets/images/products/product03.jpg" className="img-fluid" alt=""/>
              </a>
              <div className="product-hover-icons">
                <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt"></span></a>
                <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt"></span> </a>
                <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt"></span> </a>
                <a href="#" data-tooltip="Quick view" data-toggle = "modal" data-target="#quick-view-modal-container"> <span className="icon_search"></span> </a>
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
          <div className="gf-product shop-list-view-product">
            <div className="image">
              <a href="single-product.html">
                <span className="onsale">Sale!</span>
                <img src="assets/images/products/product03.jpg" className="img-fluid" alt=""/>
              </a>
              <div className="product-hover-icons">
                <a href="#" data-tooltip="Quick view" data-toggle = "modal" data-target="#quick-view-modal-container"> <span className="icon_search"></span> </a>
              </div>
            </div>
            <div className="product-content">
              <div className="product-categories">
                <a href="shop-left-sidebar.html">Fast Foods</a>,
                <a href="shop-left-sidebar.html">Vegetables</a>
              </div>
              <h3 className="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
              <div className="price-box mb-20">
                <span className="main-price">$89.00</span>
                <span className="discounted-price">$80.00</span>
              </div>
              <p className="product-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere esse tempora magnam dolorem tenetur eos eligendi non temporibus qui enim. Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam, magni.</p>
              <div className="list-product-icons">
                <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt"></span></a>
                <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt"></span> </a>
                <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt"></span> </a>
              </div>
            </div>
          </div>
        </div>
      );
    }
}

export default Product;
