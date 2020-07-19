import React from 'react';
import { Link } from "react-router-dom";
import { getCategoryProductPath } from '../../../Helpers/Route/Route';

const images = require.context('../../../../../assets/images/products', true);

function ProductGrid(props) {
  const { match } = props;
  const { data } = props.product;
  
  return (
    <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
      <div className="gf-product shop-grid-view-product">
        <div className="image">

        <Link to={`${getCategoryProductPath(data.productUPC, match)}`}>
          {/* <span className="onsale">Sale!</span> */}
          <img src={images(`./${data.productImage}`)} className="img-fluid" alt />
        </Link>
          <div className="product-hover-icons">
            <a onClick={(e) => { }} href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
            <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
            <a id={data.productUPC}
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
            <a href="shop-left-sidebar.html">{data.primaryCategory.categoryDesc}</a>,<span> </span>
            <a href="shop-left-sidebar.html">{data.brand.brandDesc}</a>
          </div>
          <h3 className="product-title"><a href="#">{data.productDesc}</a></h3>
          <div className="price-box">
            <span className="main-price">${data.productRetail}</span>
            <span className="discounted-price">${data.productMarkdown}</span>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProductGrid;