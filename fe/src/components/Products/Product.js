import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class Product extends Component {

  render() {
    return (
      <div class="single-product-content ">
        <div class="container">
            <div class="single-product-content-container mb-35">
                <div class="row">
                    <div class="col-lg-6 col-md-12 col-xs-12">
                                    <div class="product-image-slider d-flex flex-custom-xs-wrap flex-sm-nowrap align-items-center mb-sm-35">

                                        <div class="product-small-image-list">
                                            <div class="nav small-image-slider-single-product" role="tablist">
                                            <div class="single-small-image img-full">
                                                <a data-toggle="tab" id="single-slide-tab-1" href="#single-slide1"><img src="assets/images/big-product-image/product04.jpg"
                                                class="img-fluid" alt="" /></a>
                                            </div>
                                            <div class="single-small-image img-full">
                                                <a data-toggle="tab" id="single-slide-tab-2" href="#single-slide2"><img src="assets/images/big-product-image/product05.jpg"
                                                    class="img-fluid" alt="" /></a>
                                                </div>
                                            <div class="single-small-image img-full">
                                                <a data-toggle="tab" id="single-slide-tab-3" href="#single-slide3"><img src="assets/images/big-product-image/product06.jpg"
                                                    class="img-fluid" alt="" /></a>
                                                </div>
                                                <div class="single-small-image img-full">
                                                    <a data-toggle="tab" id="single-slide-tab-4" href="#single-slide4"><img src="assets/images/big-product-image/product07.jpg"
                                                    class="img-fluid" alt="" /></a>
                                                </div>
                                            </div>
                                           </div>
                                        <div class="tab-content product-large-image-list">
                                            <div class="tab-pane fade show active" id="single-slide1" role="tabpanel" aria-labelledby="single-slide-tab-1">
                                                <div class="single-product-img easyzoom img-full">
                                                    <img src="assets/images/big-product-image/product04.jpg" class="img-fluid" alt="" />
                                                    <a href="assets/images/big-product-image/product04.jpg" class="big-image-popup"><i class="fa fa-search-plus"></i></a>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="single-slide2" role="tabpanel" aria-labelledby="single-slide-tab-2">
                                                <div class="single-product-img easyzoom img-full">
                                                    <img src="assets/images/big-product-image/product05.jpg" class="img-fluid" alt="" />
                                                    <a href="assets/images/big-product-image/product05.jpg" class="big-image-popup"><i class="fa fa-search-plus"></i></a>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="single-slide3" role="tabpanel" aria-labelledby="single-slide-tab-3">
                                                <div class="single-product-img easyzoom img-full">
                                                    <img src="assets/images/big-product-image/product06.jpg" class="img-fluid" alt="" />
                                                    <a href="assets/images/big-product-image/product06.jpg" class="big-image-popup"><i class="fa fa-search-plus"></i></a>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="single-slide4" role="tabpanel" aria-labelledby="single-slide-tab-4">
                                                <div class="single-product-img easyzoom img-full">
                                                    <img src="assets/images/big-product-image/product07.jpg" class="img-fluid" alt="" />
                                                    <a href="assets/images/big-product-image/product07.jpg" class="big-image-popup"><i class="fa fa-search-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-12 col-xs-12">
                                    <div class="product-feature-details">
                                        <h2 class="product-title mb-15">Kaoreet lobortis sagittis laoreet</h2>

                                        <p class="product-rating">
                                            <i class="fa fa-star active"></i>
                                            <i class="fa fa-star active"></i>
                                            <i class="fa fa-star active"></i>
                                            <i class="fa fa-star active"></i>
                                            <i class="fa fa-star"></i>

                                            <a href="#">(1 customer review)</a>
                                        </p>

                                        <h2 class="product-price mb-15">
                                            <span class="main-price">$12.90</span>
                                            <span class="discounted-price"> $10.00</span>
                                        </h2>

                                        <p class="product-description mb-20">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco,Proin lectus ipsum, gravida et mattis vulputate, tristique ut lectus</p>


                                        <div class="cart-buttons mb-20">
                                            <div class="pro-qty mr-20 mb-xs-20">
                                                <input type="text" value="1" />
                                            </div>
                                            <div class="add-to-cart-btn">
                                                <a href="#"><i class="fa fa-shopping-cart"></i> Add to Cart</a>
                                            </div>
                                        </div>

                                        <div class="single-product-action-btn mb-20">
                                            <a href="#" data-tooltip="Add to wishlist"> <span class="icon_heart_alt"></span> Add to wishlist</a>
                                            <a href="#" data-tooltip="Add to compare"> <span class="arrow_left-right_alt"></span> Add to compare</a>
                                        </div>


                                        <div class="single-product-category mb-20">
                                            <h3>Categories: <span><a href="shop-left-sidebar.html">Fast Foods</a>, <a href="shop-left-sidebar.html">Vegetables</a></span></h3>
                                        </div>


                                        <div class="social-share-buttons">
                                            <h3>share this product</h3>
                                            <ul>
                                                <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a class="google-plus" href="#"><i class="fa fa-google-plus"></i></a></li>
                                                <li><a class="pinterest" href="#"><i class="fa fa-pinterest"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
      );
    }
}

export default Product;
