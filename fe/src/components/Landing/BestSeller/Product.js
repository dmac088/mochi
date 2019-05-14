import React from 'react';
const $ = window.$;

export const  Product = (props) => {
  return (
    <div className="best-seller-sub-product">
      <div className="row">
        <div className="col-lg-4 pl-0 pr-0">
          <div className="image">
            <a href="single-product.html">
              <img src="assets/images/products/product01.jpg" className="img-fluid" alt="" />
            </a>
          </div>
        </div>
        <div className="col-lg-8 pl-0 pr-0">
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
    </div>
  );
}
