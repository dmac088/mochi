
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class TopRatedSidebar extends Component {



  render() {
    return (
      <div class="sidebar mb-35">
        <h3 class="sidebar-title">Top rated products</h3>

        <!--=======  top rated product container  =======-->

        <div class="top-rated-product-container">
          <!--=======  single top rated product  =======-->

          <div class="single-top-rated-product d-flex align-content-center">
            <div class="image">
              <a href="single-product.html">
                <img src="assets/images/products/product01.jpg" class="img-fluid" alt="">
              </a>
            </div>
            <div class="content">
              <p><a href="single-product.html">Eodem modo vel mattis</a></p>
              <p class="product-rating">
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
              </p>

              <p class="product-price">
                <span class="discounted-price"> $10.00</span>
                <span class="main-price">$12.90</span>

              </p>
            </div>
          </div>

          <!--=======  End of single top rated product  =======-->
          <!--=======  single top rated product  =======-->

          <div class="single-top-rated-product d-flex align-content-center">
            <div class="image">
              <a href="single-product.html">
                <img src="assets/images/products/product02.jpg" class="img-fluid" alt="">
              </a>
            </div>
            <div class="content">
              <p><a href="single-product.html">Mirum est notare tellus</a></p>
              <p class="product-rating">
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
              </p>

              <p class="product-price">
                <span class="discounted-price"> $10.00</span>
                <span class="main-price">$12.90</span>

              </p>
            </div>
          </div>

          <!--=======  End of single top rated product  =======-->
          <!--=======  single top rated product  =======-->

          <div class="single-top-rated-product d-flex align-content-center">
            <div class="image">
              <a href="single-product.html">
                <img src="assets/images/products/product03.jpg" class="img-fluid" alt="">
              </a>
            </div>
            <div class="content">
              <p><a href="single-product.html">Aliquam lobortis est turpis</a></p>
              <p class="product-rating">
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
                <i class="fa fa-star active"></i>
              </p>

              <p class="product-price">
                <span class="discounted-price"> $10.00</span>
                <span class="main-price">$12.90</span>

              </p>
            </div>
          </div>

          <!--=======  End of single top rated product  =======-->

        </div>

        <!--=======  End of top rated product container  =======-->
      </div>
    );
  }
}
