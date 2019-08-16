import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { connect } from 'react-redux';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
import * as cartSelector from '../../services/cart/selectors';
import * as cartService from '../../services/cart';
import { homeRouteString, routeCart, routeCheckout, routeSingleProduct, createRouteProps } from '../../services/helpers/routeHelper';
import { productImagePath } from '../../services/helpers/imageHelper';
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
    const { cart, match, history, location} = this.props;
    const routeProps = createRouteProps(history, match, location);
    return(
      <div onMouseEnter={this.setinContainer} onMouseLeave={this.setNotinContainer} className="shopping-cart" id="shopping-cart">
          <a onClick={(e) => routeCart(e, routeProps)} href="#">
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
    if(!element) { return }
    Velocity(element, 'slideDown', { duration: 1000 }).then(callback);
  }

  componentWillLeave (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(!element) { return }
    Velocity(element, 'slideUp', { duration: 1000 }).then(callback);
  }

  setContainer = (c) => {
  	this.container = c;
  }

  removeItem = (e) => {
    e.preventDefault();
    cartService.removeFromCart(cartSelector.get(), e.currentTarget.id);
  }

  renderCartItems = (cart) => {
      const routeProps = createRouteProps(this.props.history, this.props.match, this.props.location);
      return cart.items.map(product => {
          return(
            <div key={product.productUPC} className="cart-float-single-item d-flex">
              <span className="remove-item">
                <a id={product.productUPC} onClick={this.removeItem}  href="#">
                  <i className="fa fa-times" />
                </a>
              </span>
              <div className="cart-float-single-item-image">
                <a id={product.productUPC} href="#" onClick={(e) => routeSingleProduct(e, null, routeProps)}><img src={productImagePath + product.productImage} className="img-fluid" alt="" /></a>
              </div>
              <div className="cart-float-single-item-desc">
                <p className="product-title">
                  <a id={product.productUPC} href="#" onClick={(e) => routeSingleProduct(e, null, routeProps)}>{product.productDesc} </a></p>
                <p className="price"><span className="count">{product.quantity}x</span> ${product.productMarkdown}</p>
              </div>
            </div>
          )
      });
  }

  	render() {
      const { cart, match, history, location} = this.props;
      const routeProps = createRouteProps(history, match, location);
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
              <a onClick={(e) => routeCheckout(e, routeProps)} href="#">Checkout</a>
              <a onClick={(e) => routeCart(e, routeProps)} href="#">View Cart</a>
            </div>
          </div>
      </div>
      )
  	}
}

export default withRouter(connect(state => ({
    cart: state.services.cart,
}))(HeaderCartSummary));
