import React from 'react';

function Product() {
  return (
    <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
        <div className="gf-product shop-list-view-product">
          <div className="image">
            <a onClick={(e) => {}} id={product.productUPC} href="#">
              <span className="onsale">Sale!</span>
              <img src={productImagePath + product.productImage} className="img-fluid" alt />
            </a>
            <div className="product-hover-icons">
              <a id={product.productUPC}
                 onClick={setCurrentProductId}
                 href="#"
                 data-tooltip="Quick view"
                 data-toggle="modal"
                 data-target="#quick-view-modal-container">
                 <span className="icon_search" />
              </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">{product.primaryCategoryPath}</a>,<span> </span>
              <a href="shop-left-sidebar.html">{product.brandDesc}</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">{product.productDesc}</a></h3>
            <div className="price-box mb-20">
              <span className="main-price">${product.productRetail}</span>
              <span className="discounted-price">${product.productMarkdown}</span>
            </div>
            <p className="product-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere esse tempora magnam dolorem tenetur eos eligendi non temporibus qui enim. Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam, magni.</p>
            <div className="list-product-icons">
              <a onClick={(e) => {}} href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
            </div>
          </div>
        </div>
      </div>
  )
}

export default Product;