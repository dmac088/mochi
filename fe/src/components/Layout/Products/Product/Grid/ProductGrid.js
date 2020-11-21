import React from 'react';
import { Link } from "react-router-dom";
import { getCategoryProductPath } from '../../../Helpers/Route/Route';

const images = require.context('../../../../../assets/images/products', true);



function ProductGrid(props) {
  const { match, toggleQuickView, addToBag, product } = props;

  return (
    <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
      <div className="gf-product shop-grid-view-product">
        <div className="image">
        {!product.data.inStock && <span className="onsale">Out Of Stock!</span>}
        <Link to={`${getCategoryProductPath(match, product.data.productUPC)}`}>
          {/* <span className="onsale">Sale!</span> */}
          <img src={product._links.defaultImage.href} className="img-fluid" />
        </Link>
          <div className="product-hover-icons">
          
            <a id={product.data.productUPC} onClick={addToBag} href="#" data-tooltip="Add to bag"> 
              <span id={product.data.productUPC} className="icon_cart_alt" />
            </a>
            {/* <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a> */}
            <a id={product.data.productUPC}
              onClick={(e) => toggleQuickView(e, product)}
              href="#"
              data-tooltip="Quick view"
              data-toggle="modal"
              data-target="#quick-view-modal-container"> 
              <span id={product.data.productUPC} className="icon_search" />
              </a>
          </div>
        </div>
        <div className="product-content">
          <div className="product-categories">
            <a href="#">{product.data.categoriesList}</a>,<span> </span>
            <a href="#">{product.data.brandDesc}</a>
          </div>
          <h3 className="product-title"><a href="#">{product.data.productDesc}</a></h3>
          <div className="price-box">
            <span className="main-price">${product.data.productRetail}</span>
            <span className="discounted-price">${product.data.productMarkdown}</span>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProductGrid;