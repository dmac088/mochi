import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { connect } from 'react-redux';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
import * as cartSelector from '../../services/cart/selectors';
import * as cartService from '../../services/cart';
import { homeRouteString, routeCart, routeCheckout, routeSingleProduct } from '../../services/helpers/RouteHelper';
const $ = window.$;

class HeaderCartSummary extends Component {

  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      inContainer: false,
    };
  }

  setinContainer = () => {
    this.setState({
      inContainer: true,
      visible: true,
    });
  }

  setNotinContainer = () => {;
    this.setState({ inContainer: false });
    setTimeout(() => {
      if(!(this.state.inContainer)) {
        this.setState({
          visible: false,
        });
      }
    }, 500);
  }

  render() {
    const { cart, match, history} = this.props;
    return(
      <div onMouseEnter={this.setinContainer} onMouseLeave={this.setNotinContainer} className="shopping-cart" id="shopping-cart">
          <a onClick={(e) => routeCart(e, match, history)} href="#">
            <div className="cart-icon d-inline-block">
              <span className="icon_bag_alt" />
            </div>
            <div className="cart-info d-inline-block">
              <p>Shopping Cart
                <span>
                  {cart.totalItems} items - ${cart.totalAmount}
                </span>
              </p>
            </div>
         </a>
        <ReactTransitionGroup>
          { this.state.visible ? <Accordion
                                    {...this.props}
                                /> : null }
        </ReactTransitionGroup>
      </div>
    );
  }
}

class Accordion extends React.Component {

  componentWillEnter (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    Velocity(element, 'slideDown', { duration: 1000 }).then(callback);
  }

  componentWillLeave (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    Velocity(element, 'slideUp', { duration: 1000 }).then(callback);
  }

  setContainer = (c) => {
  	this.container = c;
  }

  removeItem = (e) => {
    e.preventDefault();
    cartService.removeFromCart(cartSelector.get(), Number(e.currentTarget.id));
  }

  renderCartItems = (cart) => {
      const { match, history} = this.props;
      return cart.items.map(product => {
          return(
            <div key={product.productId} className="cart-float-single-item d-flex">
              <span className="remove-item">
                <a id={product.productId} onClick={this.removeItem}  href="#">
                  <i className="fa fa-times" />
                </a>
              </span>
              <div className="cart-float-single-item-image">
                <a id={product.productId} href="#" onClick={(e) => routeSingleProduct(e, match, history)}><img src={product.productImage} className="img-fluid" alt="" /></a>
              </div>
              <div className="cart-float-single-item-desc">
                <p className="product-title">
                  <a id={product.productId} href="#" onClick={(e) => routeSingleProduct(e, match, history)}>{product.productDesc} </a></p>
                <p className="price"><span className="count">{product.quantity}x</span> ${product.productMarkdown}</p>
              </div>
            </div>
          )
      });
  }

  	render() {
      const { cart, match, history} = this.props;
  		return (
        <div className="cart-floating-box" id="cart-floating-box" ref={this.setContainer}>
          <div className="cart-items">
            {this.renderCartItems(cart)}
          </div>
          <div className="cart-calculation">
            <div className="calculation-details">
              <p className="total">Subtotal <span>${cart.totalAmount}</span></p>
            </div>
            <div className="floating-cart-btn text-center">
              <a onClick={(e) => routeCheckout(e, match, history)} href="#">Checkout</a>
              <a onClick={(e) => routeCart(e, match, history)} href="#">View Cart</a>
            </div>
          </div>
      </div>
      )
  	}
}

export default withRouter(connect(state => ({
    cart: state.services.cart,
}))(HeaderCartSummary));
