import React from 'react';

const images = require.context('../../../../assets/images/products', true);

function Product(props) {
  const { product } = props.product;
  return (
    <div className="gf-product tab-slider-sub-product">
      <div className="image">
        <a id="#test" href="#">
          <span className="onsale">Sale!</span>
          <img src={images(`./${product.productImage}`)} className="img-fluid" alt="" />
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
          <a id="#test" href="#">{product.primaryCategory.categoryDesc}</a>,<span> </span>
          <a id="#test" href="#">{product.brand.brandDesc}</a>
        </div>
        <h3 className="product-title">
          <a id="#test" href="#">
            {product.productDesc}
            </a>
        </h3>
        <div className="price-box">
          <span className="main-price">{product.productRetail}</span>
          <span className="discounted-price">{product.productMarkdown}</span>
        </div>
      </div>
    </div>
  );
}

export default Product;
