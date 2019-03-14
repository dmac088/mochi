import React, { Component } from 'react';
import * as cartService from '../../services/cart';
import * as cartSelector from '../../services/cart/selectors';
import * as productApi from '../../data/products/api';

class HighlightedProduct extends Component {

  constructor(props){
		super(props);
    const { product } = this.props;
    this.state = {
			"product": product,
			"quantity": 1,
    }
	}

  addToCart = (e) => {
    e.preventDefault();
    const { product, quantity } = this.state;
    product.quantity = quantity;
    cartService.addToCart(cartSelector.get(),
                          product,
                          ()=>{console.log("addToCart complete!")});
  }

  render() {
    const { product } = this.props;
    return (
      <div className="gf-product tab-slider-sub-product">
        <div className="image">
          <a href="single-product.html">
            <span className="onsale">Sale!</span>
            <img src={product.productImage} className="img-fluid" alt="" />
          </a>
          <div className="product-hover-icons">
            <a onClick={this.addToCart} className="active" href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
            <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
            <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
            <a id={product.productId} onClick={this.props.setCurrentProductId} href="#" data-tooltip="Quick view" data-toggle="modal" data-target={"#modal-" + product.productId} >
              <span id={product.productId} className="icon_search" />
            </a>
          </div>
        </div>
        <div className="product-content">
          <div className="product-categories">
            <a href="shop-left-sidebar.html">Fast Foods</a>,
            <a href="shop-left-sidebar.html">Vegetables</a>
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

export default HighlightedProduct;
