import React from 'react';
import product04 from "../../../../assets/images/products/product04.jpg"

function Product() {
  return (
    <div className="gf-product tab-slider-sub-product">
      <div className="image">
        <a id="#test" href="#">
          <span className="onsale">Sale!</span>
          <img src={product04} className="img-fluid" alt="" />
        </a>
        <div className="product-hover-icons">
          <a className="active" href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
          <a id="#test" href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
          <a id="#test" href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
          <a id="#test" href="#" data-tooltip="Quick view" data-toggle="modal" data-target={"#modal-"} >
            <span className="icon_search" />
          </a>
        </div>
      </div>
      <div className="product-content">
        <div className="product-categories">
          <a id="#test" href="#">Dummy Category</a>,<span> </span>
          <a id="#test" href="#">Dummy Brand</a>
        </div>
        <h3 className="product-title">
          <a id="#test" href="#">
            Test product description
            </a>
        </h3>
        <div className="price-box">
          <span className="main-price">10</span>
          <span className="discounted-price">8</span>
        </div>
      </div>
    </div>
  );
}

export default Product;
