import React from 'react';

const images = require.context('../../../../../assets/images/products', true);

function ProductList(props) {
  const { data } = props.product;

  return (

    <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
      <div className="gf-product shop-list-view-product">
        <div className="image">
          <a onClick={(e) => { }} id={data.productUPC} href="#">
            <span className="onsale">Sale!</span>
            <img src={images(`./${data.productImage}`)} className="img-fluid" alt />
          </a>
          <div className="product-hover-icons">
            <a id={data.productUPC}
              onClick={() => {}}
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
            <a href="shop-left-sidebar.html">{data.primaryCategoryPath}</a>,<span> </span>
            <a href="shop-left-sidebar.html">{data.brandDesc}</a>
          </div>
          <h3 className="product-title"><a href="single-data.html">{data.productDesc}</a></h3>
          <div className="price-box mb-20">
            <span className="main-price">${data.productRetail}</span>
            <span className="discounted-price">${data.productMarkdown}</span>
          </div>
          <p className="product-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere esse tempora magnam dolorem tenetur eos eligendi non temporibus qui enim. Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam, magni.</p>
          <div className="list-product-icons">
            <a onClick={(e) => { }} href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
            <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
            <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProductList;