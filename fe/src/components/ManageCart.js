import React from 'react';
import * as cartSelector from '../services/cart/selectors';
import * as cartService from '../services/cart';
import { Table } from 'react-bootstrap';
import Counter from './Counter';

    const removeItem = (event) => {
      let cart = cartSelector.get();
      cartService.removeFromCart(cart, Number(event.target.id));
    }

    const ManageCart = (props) => {
      return renderCart(props.cart.cart);
    }

    const renderCart = (cart) => {
        return(
          <div className="container">
              <Table responsive>
                <thead>
                  <tr>
                    <th scope="col">Product</th>
                    <th scope="col" width={120}>Name</th>
                    <th scope="col" width={120}>Quantity</th>
                    <th scope="col" width={120}>Price</th>
                    <th scope="col" width={200} className="text-right">Action</th>
                  </tr>
                </thead>
                <tbody>
                  {renderCartItems(cart)}
                </tbody>
              </Table>
          </div>
        )
    }

    const incrementQuantity = () => {

    }

    const decrementQuantity = () => {
      
    }


    const renderCartItems = (cart) => {
          return cart.items.map(product => {
              return(
                <tr key={product.id}>
                  <td>
                    <figure className="media">
                      <div className="product">
                        <img className="product-image" src={product.image} />
                      </div>
                    </figure>
                  </td>
                  <td>
                    <figcaption className="media-body">
                      <h6 className="title text-truncate">{product.name}</h6>
                    </figcaption>
                  </td>
                  <td>
                    <Counter
                      productQty={product.quantity}
                    />
                  </td>
                  <td>
                    <div className="price-wrap">
                      <p className="product-price">{product.price}</p>
                      <small className="text-muted"></small>
                    </div> {/* price-wrap .// */}
                  </td>
                  <td className="text-right">
                    <button
                      id={product.id}
                      className="btn btn-outline-danger"
                      onClick={removeItem}>
                        × Remove
                    </button>
                  </td>
                </tr>
              )
          });
    }

export default ManageCart;
