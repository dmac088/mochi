import React from 'react';
import { connect } from 'react-redux';
import Slider from "react-slick";
import { withRouter } from 'react-router-dom';
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/Helper';
import * as cartSelector from '../../services/cart/selectors';
import * as cartService from '../../services/cart';
import * as productApi from '../../data/products/api';
import { routeSingleProduct, createRouteProps } from '../../services/helpers/RouteHelper';
const $ = window.$;


  export const Product = withRouter(({history, ...props}) => {
    const { category, product, setCurrentProductId, match, history, location } = props;
    const routeProps = createRouteProps(history, match, location);
    if(!product) {return null;}
    return (
        <div key={product.productId} className="gf-product banner-slider-product">
          <div className="image">
            <a id={product.productId} onClick={(e) => routeSingleProduct(e, category.categoryDesc, routeProps)} href="#">
              <span className="onsale">Sale!</span>
              <img src={product.productImage} className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a  id={product.productId}
                  onClick={setCurrentProductId}
                  href="#"
                  data-tooltip="Quick view"
                  data-toggle="modal"
                  data-target="#quick-view-modal-container">
                <span id={product.productId} className="icon_search" />
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
