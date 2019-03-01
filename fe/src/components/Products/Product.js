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

                    <div class="single-product-tab-section mb-35">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="tab-slider-wrapper">
                                            <nav>
                                                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                                    <a class="nav-item nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                                                    aria-selected="true">Description</a>
                                                    <a class="nav-item nav-link" id="features-tab" data-toggle="tab" href="#features" role="tab"
                                                    aria-selected="false">Features</a>
                                                    <a class="nav-item nav-link" id="review-tab" data-toggle="tab" href="#review" role="tab"
                                                    aria-selected="false">Reviews (3)</a>
                                                </div>
                                            </nav>
                                            <div class="tab-content" id="nav-tabContent">
                                                <div class="tab-pane fade show active" id="description" role="tabpanel" aria-labelledby="description-tab">
                                                    <p class="product-desc">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Itaque obcaecati tempore reiciendis neque facere! Eos, necessitatibus? Fugit iure veritatis quidem velit quaerat quos qui pariatur dolore facilis, aliquid quae voluptatibus dicta. Quae harum velit hic molestias, eius ab cum quidem voluptates modi maiores laboriosam iusto excepturi ex, recusandae aut, facere earum ad vero aperiam. Minima dolores dignissimos ab ipsam atque placeat sapiente officia debitis nobis porro at quia veritatis, quidem id repudiandae ex tempore non. Enim soluta explicabo atque adipisci itaque.</p>
                                                </div>
                                                <div class="tab-pane fade" id="features" role="tabpanel" aria-labelledby="features-tab">
                                                    <table class="table-data-sheet">
                                                        <tbody>
                                                            <tr class="odd">

                                                                <td>Name</td>
                                                                <td>Kaoreet lobortis sagittis laoreet</td>
                                                            </tr>
                                                            <tr class="even">

                                                                <td>Color</td>
                                                                <td>Yellow</td>
                                                            </tr>

                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
                                                    <div class="product-ratting-wrap">
                                                        <div class="pro-avg-ratting">
                                                            <h4>4.5 <span>(Overall)</span></h4>
                                                            <span>Based on 9 Comments</span>
                                                        </div>
                                                        <div class="ratting-list">
                                                            <div class="sin-list float-left">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <span>(5)</span>
                                                            </div>
                                                            <div class="sin-list float-left">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <span>(3)</span>
                                                            </div>
                                                            <div class="sin-list float-left">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <span>(1)</span>
                                                            </div>
                                                            <div class="sin-list float-left">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <span>(0)</span>
                                                            </div>
                                                            <div class="sin-list float-left">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <i class="fa fa-star-o"></i>
                                                                <span>(0)</span>
                                                            </div>
                                                        </div>
                                                        <div class="rattings-wrapper">

                                                            <div class="sin-rattings">
                                                                <div class="ratting-author">
                                                                    <h3>Cristopher Lee</h3>
                                                                    <div class="ratting-star">
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <span>(5)</span>
                                                                    </div>
                                                                </div>
                                                                <p>enim ipsam voluptatem quia voluptas sit
                                                                    aspernatur aut odit aut fugit, sed quia res eos
                                                                    qui ratione voluptatem sequi Neque porro
                                                                    quisquam est, qui dolorem ipsum quia dolor sit
                                                                amet, consectetur, adipisci veli</p>
                                                            </div>

                                                            <div class="sin-rattings">
                                                                <div class="ratting-author">
                                                                    <h3>Nirob Khan</h3>
                                                                    <div class="ratting-star">
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <span>(5)</span>
                                                                    </div>
                                                                </div>
                                                                <p>enim ipsam voluptatem quia voluptas sit
                                                                    aspernatur aut odit aut fugit, sed quia res eos
                                                                    qui ratione voluptatem sequi Neque porro
                                                                    quisquam est, qui dolorem ipsum quia dolor sit
                                                                amet, consectetur, adipisci veli</p>
                                                            </div>

                                                            <div class="sin-rattings">
                                                                <div class="ratting-author">
                                                                    <h3>MD.ZENAUL ISLAM</h3>
                                                                    <div class="ratting-star">
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <i class="fa fa-star"></i>
                                                                        <span>(5)</span>
                                                                    </div>
                                                                </div>
                                                                <p>enim ipsam voluptatem quia voluptas sit
                                                                    aspernatur aut odit aut fugit, sed quia res eos
                                                                    qui ratione voluptatem sequi Neque porro
                                                                    quisquam est, qui dolorem ipsum quia dolor sit
                                                                amet, consectetur, adipisci veli</p>
                                                            </div>

                                                        </div>
                                                        <div class="ratting-form-wrapper fix">
                                                            <h3>Add your Comments</h3>
                                                            <form action="#">
                                                                <div class="ratting-form row">
                                                                    <div class="col-12 mb-15">
                                                                        <h5>Rating:</h5>
                                                                        <div class="ratting-star fix">
                                                                            <i class="fa fa-star-o"></i>
                                                                            <i class="fa fa-star-o"></i>
                                                                            <i class="fa fa-star-o"></i>
                                                                            <i class="fa fa-star-o"></i>
                                                                            <i class="fa fa-star-o"></i>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-6 col-12 mb-15">
                                                                        <label for="name">Name:</label>
                                                                        <input id="name" placeholder="Name" type="text" />
                                                                    </div>
                                                                    <div class="col-md-6 col-12 mb-15">
                                                                        <label for="email">Email:</label>
                                                                        <input id="email" placeholder="Email" type="text" />
                                                                    </div>
                                                                    <div class="col-12 mb-15">
                                                                        <label for="your-review">Your Review:</label>
                                                                        <textarea name="review" id="your-review"
                                                                        placeholder="Write a review"></textarea>
                                                                    </div>
                                                                    <div class="col-12">
                                                                        <input value="add review" type="submit" />
                                                                    </div>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
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
