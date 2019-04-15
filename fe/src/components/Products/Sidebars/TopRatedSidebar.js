
import React from 'react';

export const TopRatedSidebar = (props) => {
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Top rated products</h3>
        <div className="top-rated-product-container">
          <div className="single-top-rated-product d-flex align-content-center">
            <div className="image">
              <a href="single-product.html">
                <img src="assets/images/products/product01.jpg" className="img-fluid" alt=""/>
              </a>
            </div>
            <div className="content">
              <p><a href="single-product.html">Eodem modo vel mattis</a></p>
              <p className="product-rating">
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
              </p>

              <p className="product-price">
                <span className="discounted-price"> $10.00</span>
                <span className="main-price">$12.90</span>

              </p>
            </div>
          </div>
          <div className="single-top-rated-product d-flex align-content-center">
            <div className="image">
              <a href="single-product.html">
                <img src="assets/images/products/product02.jpg" className="img-fluid" alt=""/>
              </a>
            </div>
            <div className="content">
              <p><a href="single-product.html">Mirum est notare tellus</a></p>
              <p className="product-rating">
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
              </p>

              <p className="product-price">
                <span className="discounted-price"> $10.00</span>
                <span className="main-price">$12.90</span>

              </p>
            </div>
          </div>
          <div className="single-top-rated-product d-flex align-content-center">
            <div className="image">
              <a href="single-product.html">
                <img src="assets/images/products/product03.jpg" className="img-fluid" alt=""/>
              </a>
            </div>
            <div className="content">
              <p><a href="single-product.html">Aliquam lobortis est turpis</a></p>
              <p className="product-rating">
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
                <i className="fa fa-star active"></i>
              </p>

              <p className="product-price">
                <span className="discounted-price"> $10.00</span>
                <span className="main-price">$12.90</span>

              </p>
            </div>
          </div>
        </div>
      </div>
    );
  }
