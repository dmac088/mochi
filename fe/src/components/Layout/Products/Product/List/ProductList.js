import React from 'react';
import { Link } from "react-router-dom";
import { getCategoryProductPath } from '../../../Helpers/Route/Route';

const images = require.context('../../../../../assets/images/products', true);

function ProductList(props) {
  const { match, toggleQuickView, addToBag, product} = props;

  return (

    <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
      <div className="gf-product shop-list-view-product">
        <div className="image">
          <Link to={`${getCategoryProductPath(match, product.data.productUPC)}`}>
            {/* <span className="onsale">Sale!</span> */}
            <img src={product._links.defaultImage.href} className="img-fluid" />
          </Link>
          <div className="product-hover-icons">
            <a id={product.data.productUPC}
              onClick={(e) => toggleQuickView(e, product)}
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
            <a href="shop-left-sidebar.html">{product.data.primaryCategoryPath}</a>,<span> </span>
            <a href="shop-left-sidebar.html">{product.data.brandDesc}</a>
          </div>
          <h3 className="product-title"><a href="single-product.data.html">{product.data.productDesc}</a></h3>
          <div className="price-box mb-20">
            <span className="main-price">${product.data.productRetail}</span>
            <span className="discounted-price">${product.data.productMarkdown}</span>
          </div>
          <p className="product-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere esse tempora magnam dolorem tenetur eos eligendi non temporibus qui enim. Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam, magni.</p>
          <div className="list-product-icons">
            <a id={product.data.productUPC} href="#" onClick={addToBag}  data-tooltip="Add to bag"> <span className="icon_cart_alt" /></a>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProductList;