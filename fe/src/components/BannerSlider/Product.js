import React, { Component } from 'react';
import { connect } from 'react-redux';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/Helper';
import * as cartSelector from '../../services/cart/selectors';
import * as cartService from '../../services/cart';
import * as productApi from '../../data/products/api';
const $ = window.$;

class Product extends Component {

  routeSingleProduct = (e) => {
    e.preventDefault();
    const categoryDesc = this.props.category.categoryDesc;
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/' + locale + '/' + currency + '/category/' + ((!categoryDesc) ? 'ALL' : categoryDesc) + '/product/' + e.currentTarget.id);
  }

  render() {
    const { product, setCurrentProductId } = this.props;
    if(!product) {return null;}
    return (
        <div key={product.productId} className="gf-product banner-slider-product">
          <div className="image">
            <a id={product.productId} onClick={this.routeSingleProduct} href="#">
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
              <a href="shop-left-sidebar.html">{this.props.categoryDesc}</a>
            </div>
            <h3 className="product-title">
              <a href="single-product.html">{product.productDesc}</a></h3>
            <div className="price-box">
              <span className="main-price">${product.productRetail}</span>
              <span className="discounted-price">${product.productMarkdown}</span>
            </div>
          </div>
        </div>
    )
  }
}

export default Product;
