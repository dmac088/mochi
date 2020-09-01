import React from 'react';
import { Link } from "react-router-dom";
import { getCategoryProductPath } from '../../Helpers/Route/Route';
import * as bagService from "../../../../services/Bag/index";
import { useDispatch } from 'react-redux';


function Product(props) {
  const { match, category, toggleQuickView, product } = props;

  const dispatch = useDispatch();

  const addToBag = (e) => {
    e.preventDefault();
    dispatch(bagService.addItem({
      "itemUPC": e.target.id,
      "itemQty": 1,
    }));
  }

  return (
    <div className="gf-product tab-slider-sub-product">
      <div className="image">
        <Link to={`${getCategoryProductPath(match, product.data.productUPC, category.data.categoryCode)}`}>
          {/* <span className="onsale">Sale!</span> */}
          <img src={product._links.defaultImage.href} alt="Image not found" className="img-fluid" />
        </Link>
        <div className="product-hover-icons">
          <a id={product.data.productUPC} href="#" className="active" onClick={addToBag} href="#" data-tooltip="Add to bag"> <span id={product.data.productUPC} className="icon_cart_alt" /></a>
          {/* <a id="#test" href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a> */}
          <a id="#test" href="#" onClick={(e) => toggleQuickView(e, product)} data-tooltip="Quick view" data-toggle="modal" data-target={"#modal-"} >
            <span className="icon_search" />
          </a>
        </div>
      </div>
      <div className="product-content">
        <div className="product-categories">
          <a id="#test" href="#">{product.data.primaryCategoryDesc}</a>,<span> </span>
          <a id="#test" href="#">{product.data.brandDesc}</a>
        </div>
        <h3 className="product-title">
          <a id="#test" href="#">
            {product.data.productDesc}
          </a>
        </h3>
        <div className="price-box">
          <span className="main-price">{product.data.productRetail}</span>
          <span className="discounted-price">{product.data.productMarkdown}</span>
        </div>
      </div>
    </div>
  );
}

export default Product;
