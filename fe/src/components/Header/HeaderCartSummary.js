import React, { Component } from 'react';
import { connect } from 'react-redux';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
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

  viewCart = (e) => {
    e.preventDefault();
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/' + locale + '/' + currency + '/Cart');
  }

  render() {
    return(
      <div onMouseEnter={this.setinContainer} onMouseLeave={this.setNotinContainer} className="shopping-cart" id="shopping-cart">
          <a onClick={this.viewCart} href="#">
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
        <ReactTransitionGroup>
          { this.state.visible ? <Accordion
                                    match={this.props.match}
                                    history={this.props.history}
                                    location={this.props.location}
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

    checkout = (e) => {
      e.preventDefault();
      const { locale, currency } = this.props.match.params;
      this.props.history.push('/' + locale + '/' + currency + '/Checkout');
    }

    viewCart = (e) => {
      e.preventDefault();
      const { locale, currency } = this.props.match.params;
      this.props.history.push('/' + locale + '/' + currency + '/Cart');
    }

  	render() {
  		return (
      <div className="cart-floating-box" id="cart-floating-box" ref={this.setContainer}>
        <div className="cart-items">
          <div className="cart-float-single-item d-flex">
            <span className="remove-item"><a href="#"><i className="fa fa-times" /></a></span>
            <div className="cart-float-single-item-image">
              <a href="single-product.html"><img src="assets/images/products/product01.jpg" className="img-fluid" alt="" /></a>
            </div>
            <div className="cart-float-single-item-desc">
              <p className="product-title"> <a href="single-product.html">Duis pulvinar obortis eleifend </a></p>
              <p className="price"><span className="count">1x</span> $20.50</p>
            </div>
          </div>
          <div className="cart-float-single-item d-flex">
            <span className="remove-item"><a href="#"><i className="fa fa-times" /></a></span>
            <div className="cart-float-single-item-image">
              <a href="single-product.html"><img src="assets/images/products/product02.jpg" className="img-fluid" alt="" /></a>
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
            <a onClick={this.checkout} href="#">Checkout</a>
            <a onClick={this.viewCart} href="#">View Cart</a>
          </div>
        </div>
      </div>
      )
  	}

  }

export default connect(state => ({
    cart: state.services.cart,
}))(HeaderCartSummary);
