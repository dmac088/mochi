import React from 'react';
import { Link } from "react-router-dom";
import { getCategoryProductPath } from '../../../Helpers/Route/Route';

const images = require.context('../../../../../assets/images/products', true);

function ProductGrid(props) {
  const { match, toggleQuickView, addToBag } = props;
  const { data } = props.product;
  
  console.log(data.productImage);
  return (
    <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
      <div className="gf-product shop-grid-view-product">
        <div className="image">

        <Link to={`${getCategoryProductPath(match, data.productUPC)}`}>
          {/* <span className="onsale">Sale!</span> */}
          <img src={images(`./${data.productImage}`)} className="img-fluid" />
        </Link>
          <div className="product-hover-icons">
            <a id={data.productUPC} onClick={addToBag} href="#" data-tooltip="Add to bag"> 
              <span id={data.productUPC} className="icon_cart_alt" />
            </a>
            {/* <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a> */}
            <a id={data.productUPC}
              onClick={(e) => toggleQuickView(e, data)}
              href="#"
              data-tooltip="Quick view"
              data-toggle="modal"
              data-target="#quick-view-modal-container"> 
              <span id={data.productUPC} className="icon_search" />
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