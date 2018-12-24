import React, {Component} from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as tokensActionCreators from '../services/session/actions';
import * as customerActionCreators from '../services/customer/actions';
import * as cartActionCreators from '../services/cart/actions';
import * as cartService from '../services/cart';
import config from '../config/config';
import Counter from './Counter';

  const removeItem = (event, cart) => {
    cartService.removeFromCart(cart, Number(event.target.id));
  }

  const ManageCart = (cart) => {
    renderCart(cart);
  }

  const renderCart = (cart) => {
        return(
          <div className="container">
            <br />
            <hr />
            <div className="card">
              <table className="table table-hover shopping-cart-wrap">
                <thead className="text-muted">
                  <tr>
                    <th scope="col">Product</th>
                    <th scope="col" width={120}>Quantity</th>
                    <th scope="col" width={120}>Price</th>
                    <th scope="col" width={200} className="text-right">Action</th>
                  </tr>
                </thead>
                <tbody>
                  {renderCartItems(cart)}
                </tbody>
              </table>
            </div>
          </div>
        )
    }


    const renderCartItems = (cart) => {
          let basketItems;
          basketItems = cart.items.map(product => {
              return(
                <tr key={product.id}>
                  <td>
                    <figure className="media">
                      <div className="img-wrap">
                        <img className="img-thumbnail img-sm" src={product.image} />
                      </div>
                      <figcaption className="media-body">
                        <h6 className="title text-truncate">{product.name}</h6>
                        <dl className="param param-inline small">
                          <dt>Size: </dt>
                          <dd>XXL</dd>
                        </dl>
                        <dl className="param param-inline small">
                          <dt>Color: </dt>
                          <dd>Orange color</dd>
                        </dl>
                      </figcaption>
                    </figure>
                  </td>
                  <td>
                    <Counter productQuantity={product.quantity}/>
                  </td>
                  <td>
                    <div className="price-wrap">
                      <var className="price">USD 145</var>
                      <small className="text-muted">(USD5 each)</small>
                    </div> {/* price-wrap .// */}
                  </td>
                  <td className="text-right">
                    <a title href className="btn btn-outline-success" data-toggle="tooltip" data-original-title="Save to Wishlist"> <i className="fa fa-heart" /></a>
                    <button
                      id={product.id}
                      className="btn btn-outline-danger"
                      onClick={this.removeItem}>
                        × Remove
                    </button>
                  </td>
                </tr>
              )
          })
    }

export default ManageCart;
