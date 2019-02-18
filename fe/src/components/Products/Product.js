import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class Product extends Component {

  render() {
    return (
      <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
          <!--=======  Grid view product  =======-->

          <div class="gf-product shop-grid-view-product">
            <div class="image">
              <a href="single-product.html">
                <span class="onsale">Sale!</span>
                <img src="assets/images/products/product03.jpg" class="img-fluid" alt="">
              </a>
              <div class="product-hover-icons">
                <a href="#" data-tooltip="Add to cart"> <span class="icon_cart_alt"></span></a>
                <a href="#" data-tooltip="Add to wishlist"> <span class="icon_heart_alt"></span> </a>
                <a href="#" data-tooltip="Compare"> <span class="arrow_left-right_alt"></span> </a>
                <a href="#" data-tooltip="Quick view" data-toggle = "modal" data-target="#quick-view-modal-container"> <span class="icon_search"></span> </a>
              </div>
            </div>
            <div class="product-content">
              <div class="product-categories">
                <a href="shop-left-sidebar.html">Fast Foods</a>,
                <a href="shop-left-sidebar.html">Vegetables</a>
              </div>
              <h3 class="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
              <div class="price-box">
                <span class="main-price">$89.00</span>
                <span class="discounted-price">$80.00</span>
              </div>
            </div>

          </div>

          <!--=======  End of Grid view product  =======-->

          <!--=======  Shop list view product  =======-->

          <div class="gf-product shop-list-view-product">
            <div class="image">
              <a href="single-product.html">
                <span class="onsale">Sale!</span>
                <img src="assets/images/products/product03.jpg" class="img-fluid" alt="">
              </a>
              <div class="product-hover-icons">
                <a href="#" data-tooltip="Quick view" data-toggle = "modal" data-target="#quick-view-modal-container"> <span class="icon_search"></span> </a>
              </div>
            </div>
            <div class="product-content">
              <div class="product-categories">
                <a href="shop-left-sidebar.html">Fast Foods</a>,
                <a href="shop-left-sidebar.html">Vegetables</a>
              </div>
              <h3 class="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
              <div class="price-box mb-20">
                <span class="main-price">$89.00</span>
                <span class="discounted-price">$80.00</span>
              </div>
              <p class="product-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere esse tempora magnam dolorem tenetur eos eligendi non temporibus qui enim. Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam, magni.</p>
              <div class="list-product-icons">
                <a href="#" data-tooltip="Add to cart"> <span class="icon_cart_alt"></span></a>
                <a href="#" data-tooltip="Add to wishlist"> <span class="icon_heart_alt"></span> </a>
                <a href="#" data-tooltip="Compare"> <span class="arrow_left-right_alt"></span> </a>
              </div>
            </div>

          </div>

        <!--=======  End of Shop list view product  =======-->
        </div>
      );
    }
}
