import React, { Component } from 'react';
import { connect } from 'react-redux';
const $ = window.$;

class HeaderCartSummary extends Component {

  componentDidMount(){
      this.initCartMenu();
  }

  initCartMenu = () => {
    $("#shopping-cart").mouseenter(function () {
      $("#cart-floating-box").stop().slideDown(1000);
    });

    $("#shopping-cart").mouseleave(function () {
      $("#cart-floating-box").stop().slideUp(1000);
    });
  }


  render() {
    return(
      <div className="shopping-cart" id="shopping-cart">
        <a href="cart.html">
          <div className="cart-icon d-inline-block">
            <span className="icon_bag_alt" />
          </div>
          <div className="cart-info d-inline-block">
            <p>Shopping Cart
              <span>
                {this.props.cart.totalItems} items - ${this.props.cart.totalAmount}
              </span>
            </p>
          </div>
        </a>
        {/* end of shopping cart */}
        {/* cart floating box */}
        <div className="cart-floating-box" id="cart-floating-box">
          <div className="cart-items">
            <div className="cart-float-single-item d-flex">
              <span className="remove-item"><a href="#"><i className="fa fa-times" /></a></span>
              <div className="cart-float-single-item-image">
                <a href="single-product.html"><img src="assets/images/products/product01.jpg" className="img-fluid" alt /></a>
              </div>
              <div className="cart-float-single-item-desc">
                <p className="product-title"> <a href="single-product.html">Duis pulvinar obortis eleifend </a></p>
                <p className="price"><span className="count">1x</span> $20.50</p>
              </div>
            </div>
            <div className="cart-float-single-item d-flex">
              <span className="remove-item"><a href="#"><i className="fa fa-times" /></a></span>
              <div className="cart-float-single-item-image">
                <a href="single-product.html"><img src="assets/images/products/product02.jpg" className="img-fluid" alt /></a>
              </div>
              <div className="cart-float-single-item-desc">
                <p className="product-title"> <a href="single-product.html">Fusce ultricies dolor vitae</a></p>
                <p className="price"><span className="count">1x</span> $20.50</p>
              </div>
            </div>
          </div>
          <div className="cart-calculation">
            <div className="calculation-details">
              <p className="total">Subtotal <span>$22</span></p>
            </div>
            <div className="floating-cart-btn text-center">
              <a href="checkout.html">Checkout</a>
              <a href="cart.html">View Cart</a>
            </div>
          </div>
        </div>
        {/* end of cart floating box */}
      </div>
    );
  }
}

export default connect(state => ({
    cart:     state.services.cart,
}))(HeaderCartSummary);
