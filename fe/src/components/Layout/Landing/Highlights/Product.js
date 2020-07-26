import React from 'react';
import * as bagService from "../../../../services/Bag/index";
import { useSelector, useDispatch } from 'react-redux';


function Product(props) {
  
  const { data } = props.product;

  const images = require.context('../../../../assets/images/products', true);
  const dispatch = useDispatch();

  const addToBag = (e) => {
    e.preventDefault();
    dispatch(bagService.addItem({
                            "productCode": e.target.id, 
                            "quantity": 1,
                        }));
  }

  return (
    <div className="gf-product tab-slider-sub-product">
      <div className="image">
        <a id="#test" href="#">
          {/* <span className="onsale">Sale!</span> */}
          <img src={images(`./${data.productImage}`)} className="img-fluid" alt="" />
        </a>
        <div className="product-hover-icons">
          <a id={data.productUPC} href="#" className="active" onClick={addToBag} href="#" data-tooltip="Add to bag"> <span id={data.productUPC} className="icon_cart_alt" /></a>
          <a id="#test" href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
          <a id="#test" href="#" data-tooltip="Quick view" data-toggle="modal" data-target={"#modal-"} >
            <span className="icon_search" />
          </a>
        </div>
      </div>
      <div className="product-content">
        <div className="product-categories">
          <a id="#test" href="#">{data.primaryCategory.categoryDesc}</a>,<span> </span>
          <a id="#test" href="#">{data.brand.brandDesc}</a>
        </div>
        <h3 className="product-title">
          <a id="#test" href="#">
            {data.productDesc}
            </a>
        </h3>
        <div className="price-box">
          <span className="main-price">{data.productRetail}</span>
          <span className="discounted-price">{data.productMarkdown}</span>
        </div>
      </div>
    </div>
  );
}

export default Product;
