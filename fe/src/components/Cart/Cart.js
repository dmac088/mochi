import React from 'react';
import { withRouter } from 'react-router-dom';
import * as cartSelector from '../../services/cart/selectors';
import * as cartService from '../../services/cart';
import { routeSingleProduct } from '../../services/helpers/routeHelper';
import { productImagePath } from '../../services/helpers/imageHelper';

  const removeItem = (e) => {
    e.preventDefault();
    cartService.removeFromCart(cartSelector.get(), e.currentTarget.id);
  }

  const renderCartProducts = (routeProps, props, cart) => {
    const { match, history } = props;
    return cart.items.map(product => {
        return(
          <tr key={product.productUPC}>
            <td className="pro-thumbnail">
              <a id={product.productUPC} href="#" onClick={(e) => routeSingleProduct(e, null, routeProps)}>
                <img src={productImagePath + product.productImage} className="img-fluid" alt="Product" />
              </a>
            </td>
            <td className="pro-title">
              <a id={product.productUPC} href="#" onClick={(e) => routeSingleProduct(e, null, routeProps)}>
                {product.productDesc}
              </a>
            </td>
            <td className="pro-price">
              <span>${product.productMarkdown}</span>
            </td>
            <td className="pro-quantity">
              <div className="pro-qty">
                <input type="text" defaultValue={product.quantity} />
              </div>
            </td>
            <td className="pro-subtotal">
              <span>${product.quantity * product.productMarkdown}</span>
            </td>
            <td className="pro-remove">
              <a id={product.productUPC} onClick={removeItem} href="#">
                <i className="fa fa-trash-o"></i>
              </a>
            </td>
          </tr>
        )
      });
  }


  export const Cart = withRouter(({history, match, location, ...props}) => {
      const { cart } = props;
			return(
        <React.Fragment>
          <div className="page-section section mb-50">
              <div className="container">
                  <div className="row">
                      <div className="col-12">
                          <form action="#">
                              <div className="cart-table table-responsive mb-40">
                                  <table className="table">
                                      <thead>
                                          <tr>
                                              <th className="pro-thumbnail">Image</th>
                                              <th className="pro-title">Product</th>
                                              <th className="pro-price">Price</th>
                                              <th className="pro-quantity">Quantity</th>
                                              <th className="pro-subtotal">Total</th>
                                              <th className="pro-remove">Remove</th>
                                          </tr>
                                      </thead>
                                      <tbody>
                                          {renderCartProducts({history, match, location}, props, cart)}
                                      </tbody>
                                  </table>
                              </div>
                          </form>
                          <div className="row">
                              <div className="col-lg-6 col-12">
                                  <div className="calculate-shipping">
                                      <h4>Calculate Shipping</h4>
                                      <form action="#">
                                          <div className="row">
                                              <div className="col-md-6 col-12 mb-25">
                                                  <select className="nice-select">
                                                      <option>Bangladesh</option>
                                                      <option>China</option>
                                                      <option>country</option>
                                                      <option>India</option>
                                                      <option>Japan</option>
                                                  </select>
                                              </div>
                                              <div className="col-md-6 col-12 mb-25">
                                                  <select className="nice-select">
                                                      <option>Dhaka</option>
                                                      <option>Barisal</option>
                                                      <option>Khulna</option>
                                                      <option>Comilla</option>
                                                      <option>Chittagong</option>
                                                  </select>
                                              </div>
                                              <div className="col-md-6 col-12 mb-25">
                                                  <input type="text" placeholder="Postcode / Zip" />
                                              </div>
                                              <div className="col-md-6 col-12 mb-25">
                                                  <input type="submit" defaultValue="Estimate" />
                                              </div>
                                          </div>
                                      </form>
                                  </div>
                                  <div className="discount-coupon">
                                      <h4>Discount Coupon Code</h4>
                                      <form action="#">
                                          <div className="row">
                                              <div className="col-md-6 col-12 mb-25">
                                                  <input type="text" placeholder="Coupon Code" />
                                              </div>
                                              <div className="col-md-6 col-12 mb-25">
                                                  <input type="submit" defaultValue="Apply Code" />
                                              </div>
                                          </div>
                                      </form>
                                  </div>
                              </div>
                              <div className="col-lg-6 col-12 d-flex">
                                  <div className="cart-summary">
                                      <div className="cart-summary-wrap">
                                          <h4>Cart Summary</h4>
                                          <p>Sub Total <span>$1250.00</span></p>
                                          <p>Shipping Cost <span>$00.00</span></p>
                                          <h2>Grand Total <span>$1250.00</span></h2>
                                      </div>
                                      <div className="cart-summary-button">
                                          <button className="checkout-btn">Checkout</button>
                                          <button className="update-btn">Update Cart</button>
                                      </div>
                                  </div>
                              </div>

                          </div>

                      </div>
                  </div>
              </div>
          </div>
        </React.Fragment>
      )
    });
