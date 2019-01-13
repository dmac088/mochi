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

    const incrementQuantity = (e) => {
      //update the redux state to increase the quantity by 1
      let cart = cartSelector.get();
      cartService.updateQuantity(cart, e.target.id, 1)

    }

    const decrementQuantity = (e) => {
      //update the redux state to reduce the quantity by 1
      let cart = cartSelector.get();
      cartService.updateQuantity(cart, e.target.id, -1)
    }

    const renderCartItems = (cart) => {

          return cart.items.map(product => {
              return(
                <tr key={product.productDTO.productId} id={product.productDTO.productId}>
                  <td>
                    <figure className="media">
                      <div  className="product">
                        <img className="product-image" src={product.productDTO.productImage} />
                      </div>
                    </figure>
                  </td>
                  <td>
                    <figcaption className="media-body">
                      <h6 className="title text-truncate">{product.productDTO.productDesc}</h6>
                    </figcaption>
                  </td>
                  <td>
                    <Counter
                      incrementQuantity={incrementQuantity}
      								decrementQuantity={decrementQuantity}
                      productId={product.productDTO.productId}
                      productQty={product.quantity}
                    />
                  </td>
                  <td>
                    <div className="price-wrap">
                      <p className="product-price">{product.productDTO.productRrp}</p>
                      <small className="text-muted"></small>
                    </div> {/* price-wrap .// */}
                  </td>
                  <td className="text-right">
                    <button
                      id={product.productDTO.productId}
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
