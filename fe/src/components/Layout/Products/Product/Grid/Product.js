import React from 'react';

const images = require.context('../../../../../assets/images/products', true);

function Product(props) {
  const { product } = props.product;
  
  return (
    <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
      <div className="gf-product shop-grid-view-product">
        <div className="image">
          <a onClick={(e) => { }} id={product.productUPC} href="#">
            <span className="onsale">Sale!</span>
            <img src={images(`./${product.productImage}`)} className="img-fluid" alt />
          </a>
          <div className="product-hover-icons">
            <a onClick={(e) => { }} href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
            <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
            <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
            <a id={product.productUPC}
              onClick={(e) => { }}
              href="#"
              data-tooltip="Quick view"
              data-toggle="modal"
              data-target="#quick-view-modal-container"> <span className="icon_search" />
            </a>
          </div>
        </div>
        <div className="product-content">
          <div className="product-categories">
            <a href="shop-left-sidebar.html">{product.primaryCategory.categoryDesc}</a>,<span> </span>
            <a href="shop-left-sidebar.html">{product.brand.brandDesc}</a>
          </div>
          <h3 className="product-title"><a href="#">{product.productDesc}</a></h3>
          <div className="price-box">
            <span className="main-price">${product.productRetail}</span>
            <span className="discounted-price">${product.productMarkdown}</span>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Product;