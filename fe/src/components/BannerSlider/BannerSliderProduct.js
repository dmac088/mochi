import React, { Component } from 'react';
import { connect } from 'react-redux';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/Helper';
import * as cartSelector from '../../services/cart/selectors';
import * as cartService from '../../services/cart';
import * as productApi from '../../data/products/api';
const $ = window.$;


class BannerSliderProduct extends Component {

  constructor(props){
    super(props);
    const { locale } = this.props.match.params;
    this.state = {
      "productId": null,
      "locale": locale,
			"product": {},
			"currentImage": "",
    }
  }

  componentDidUpdate() {
    const { locale } = this.props.match.params;
    const { productId } = this.props;
    if(productId === null) {return;}
    this.updateData(locale, productId);
  }

  getProduct = (locale, id) =>
    productApi.findById(locale, id)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .catch(()=>{
        console.log('getProducts failed!');
    });

  updateData = (locale = "en-GB", productId, isMounting = 0) => {
    if(locale === this.state.locale
      && productId === this.state.productId
      && isMounting === 0) {return;}
    this.getProduct(locale, productId)
    .then((responseJSON) => {
      this.setState({
        locale: locale,
        productId: productId,
        product: responseJSON,
        currentImage: responseJSON.productImage,
      });
    });
  }

  render() {
    const { product } = this.props;
    return (
        <div key={product.productId} className="gf-product banner-slider-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src={product.productImage} className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a id={product.productId} onClick={this.props.setCurrentProductId} href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container">
                <span className="icon_search" />
              </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">{this.props.categoryDesc}</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">{product.productDesc}</a></h3>
            <div className="price-box">
              <span className="main-price">${product.productRrp}</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
    )
  }
}

export default BannerSliderProduct;
