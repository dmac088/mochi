import React from 'react';
import { connect } from 'react-redux';
import Slider from "react-slick";
import { withRouter } from 'react-router-dom';
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/uiHelper';
import * as cartSelector from '../../services/cart/selectors';
import * as cartService from '../../services/cart';
import * as productApi from '../../data/products/api';
import { routeSingleProduct, createRouteProps } from '../../services/helpers/routeHelper';
import { productImagePath } from '../../services/helpers/imageHelper';
const $ = window.$;


  export const Product = withRouter(({history, match, location, ...props}) => {
    const { category, product, setCurrentProductId } = props;
    const routeProps = createRouteProps(history, match, location);
    if(!product) {return null;}
    return (
        <div key={product.productUPC} className="gf-product banner-slider-product">
          <div className="image">
            <a id={product.productUPC} onClick={(e) => routeSingleProduct(e, category.categoryDesc, routeProps)} href="#">
              <span className="onsale">Sale!</span>
              <img src={productImagePath + product.productImage} className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a  id={product.productUPC}
                  onClick={setCurrentProductId}
                  href="#"
                  data-tooltip="Quick view"
                  data-toggle="modal"
                  data-target="#quick-view-modal-container">
                <span id={product.productUPC} className="icon_search" />
              </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">{props.categoryDesc}</a>
            </div>
            <h3 className="product-title">
              <a href="single-product.html">{product.productDesc}</a></h3>
            <div className="price-box">
              <span className="main-price">${product.productRetail}</span>
              <span className="discounted-price">${product.productMarkdown}</span>
            </div>
          </div>
        </div>
      );
  });
