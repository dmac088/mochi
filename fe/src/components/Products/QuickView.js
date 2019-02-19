import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class QuickView extends Component {

    render() {
			return(
        <div className="modal fade quick-view-modal-container" id="quick-view-modal-container" tabindex="-1" role="dialog" aria-hidden="true">
          <div className="modal-dialog modal-dialog-centered" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div className="modal-body">
                <div className="row">
                  <div className="col-lg-5 col-md-6 col-xs-12">
                    <!-- product quickview image gallery -->
                    <div className="product-image-slider">
                      <!--Modal Tab Content Start-->
                      <div className="tab-content product-large-image-list" id="myTabContent">
                        <div className="tab-pane fade show active" id="single-slide1" role="tabpanel" aria-labelledby="single-slide-tab-1">
                          <!--Single Product Image Start-->
                          <div className="single-product-img img-full">
                            <img src="assets/images/products/product01.jpg" className="img-fluid" alt="">
                          </div>
                          <!--Single Product Image End-->
                        </div>
                        <div className="tab-pane fade" id="single-slide2" role="tabpanel" aria-labelledby="single-slide-tab-2">
                          <!--Single Product Image Start-->
                          <div className="single-product-img img-full">
                            <img src="assets/images/products/product02.jpg" className="img-fluid" alt="">
                          </div>
                          <!--Single Product Image End-->
                        </div>
                        <div className="tab-pane fade" id="single-slide3" role="tabpanel" aria-labelledby="single-slide-tab-3">
                          <!--Single Product Image Start-->
                          <div className="single-product-img img-full">
                            <img src="assets/images/products/product03.jpg" className="img-fluid" alt="">
                          </div>
                          <!--Single Product Image End-->
                        </div>
                        <div className="tab-pane fade" id="single-slide4" role="tabpanel" aria-labelledby="single-slide-tab-4">
                          <!--Single Product Image Start-->
                          <div className="single-product-img img-full">
                            <img src="assets/images/products/product04.jpg" className="img-fluid" alt="">
                          </div>
                          <!--Single Product Image End-->
                        </div>
                      </div>
                      <!--Modal Content End-->
                      <!--Modal Tab Menu Start-->
                      <div className="product-small-image-list">
                        <div className="nav small-image-slider" role="tablist">
                          <div className="single-small-image img-full">
                            <a data-toggle="tab" id="single-slide-tab-1" href="#single-slide1"><img src="assets/images/products/product01.jpg"
                              className="img-fluid" alt=""></a>
                          </div>
                          <div className="single-small-image img-full">
                            <a data-toggle="tab" id="single-slide-tab-2" href="#single-slide2"><img src="assets/images/products/product02.jpg"
                              className="img-fluid" alt=""></a>
                          </div>
                          <div className="single-small-image img-full">
                            <a data-toggle="tab" id="single-slide-tab-3" href="#single-slide3"><img src="assets/images/products/product03.jpg"
                              className="img-fluid" alt=""></a>
                          </div>
                          <div className="single-small-image img-full">
                            <a data-toggle="tab" id="single-slide-tab-4" href="#single-slide4"><img src="assets/images/products/product04.jpg"
                              alt=""></a>
                          </div>
                        </div>
                      </div>
                      <!--Modal Tab Menu End-->
                    </div>
                    <!-- end of product quickview image gallery -->
                  </div>
                  <div className="col-lg-7 col-md-6 col-xs-12">
                    <!-- product quick view description -->
                    <div className="product-feature-details">
                      <h2 className="product-title mb-15">Kaoreet lobortis sagittis laoreet</h2>

                      <h2 className="product-price mb-15">
                        <span className="main-price">$12.90</span>
                        <span className="discounted-price"> $10.00</span>
                      </h2>

                      <p className="product-description mb-20">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco,Proin lectus ipsum, gravida et mattis vulputate, tristique ut lectus</p>


                      <div className="cart-buttons mb-20">
                        <div className="pro-qty mr-10">
                          <input type="text" value="1">
                        </div>
                        <div className="add-to-cart-btn">
                          <a href="#"><i className="fa fa-shopping-cart"></i> Add to Cart</a>
                        </div>
                      </div>


                      <div className="social-share-buttons">
                        <h3>share this product</h3>
                        <ul>
                          <li><a className="twitter" href="#"><i className="fa fa-twitter"></i></a></li>
                          <li><a className="facebook" href="#"><i className="fa fa-facebook"></i></a></li>
                          <li><a className="google-plus" href="#"><i className="fa fa-google-plus"></i></a></li>
                          <li><a className="pinterest" href="#"><i className="fa fa-pinterest"></i></a></li>
                        </ul>
                      </div>
                    </div>
                    <!-- end of product quick view description -->
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
      );
    }
}

export default QuickView;
