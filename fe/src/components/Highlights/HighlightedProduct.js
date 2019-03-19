import React, { Component } from 'react';
import * as cartService from '../../services/cart';
import * as cartSelector from '../../services/cart/selectors';
import * as productApi from '../../data/products/api';

class HighlightedProduct extends Component {

  constructor(props){
		super(props);
    this.state = {
			"product": {},
			"quantity": 1,
      "currentImage": 'assets/images/spinners/default.gif',
    }
	}

  componentDidMount() {
    const { product } = this.props;
    setTimeout(() => this.setState({
                      "product": product,
                      "currentImage": product.productImage,
                    }), 1000);
  }

  addToCart = (e) => {

    e.preventDefault();
    const { product, quantity } = this.state;
    product.quantity = quantity;
    cartService.addToCart(cartSelector.get(),
                          product,
                          ()=>{console.log("addToCart complete!")});
  }

  routeSingleProduct = (e) => {
    e.preventDefault();
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/' + locale + '/' + currency + '/Product/' + e.currentTarget.id);
  }

  render() {
    const { product, setCurrentProductId } = this.props;
    const { currentImage } = this.state;
    return (
      <div className="gf-product tab-slider-sub-product">
        <div className="image">
          <a id={product.productId} onClick={this.routeSingleProduct} href="#">
            <span className="onsale">Sale!</span>
            <img src={currentImage} className="img-fluid" alt="" />
          </a>
          <div className="product-hover-icons">
            <a onClick={this.addToCart} className="active" href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
            <a id={product.productId} onClick={setCurrentProductId} href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
            <a id={product.productId} onClick={setCurrentProductId} href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
            <a id={product.productId} onClick={setCurrentProductId} href="#" data-tooltip="Quick view" data-toggle="modal" data-target={"#modal-" + product.productId} >
              <span className="icon_search" />
            </a>
          </div>
        </div>
        <div className="product-content">
          <div className="product-categories">
            <a id={product.productId} href={this.routeSingleProduct} href="#">Fast Foods</a>,
            <a id={product.productId} href={this.routeSingleProduct} href="#">Vegetables</a>
          </div>
          <h3 className="product-title">
            <a id={product.productId} onClick={this.routeSingleProduct} href="#">
              {product.productDesc}
            </a>
          </h3>
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
