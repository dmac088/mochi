import React, { Component } from 'react';
import * as cartSelector from '../services/cart/selectors';
import * as cartService from '../services/cart';
import Counter from './Counter';
import { connect } from 'react-redux';


/*
cart={this.props.cart}
            updateQuantity={this.props.updateQuantity}
*/
class ManageCart extends Component {

    removeItem = (event) => {
      cartService.removeFromCart(cartSelector.get(), Number(event.target.id));
    }

    incrementQuantity = (e) => {
      //update the redux state to increase the quantity by 1
      cartService.updateQuantity(cartSelector.get(), e.target.id, 1)
    }

    decrementQuantity = (e) => {
      //update the redux state to reduce the quantity by 1
      cartService.updateQuantity(cartSelector.get(), e.target.id, -1)
    }

    renderCartItems = () => {
          return cartSelector.get().items.map(product => {
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
                      incrementQuantity={this.incrementQuantity}
      								decrementQuantity={this.decrementQuantity}
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
                      onClick={this.removeItem}>
                        × Remove
                    </button>
                  </td>
                </tr>
              )
          });
    }

    render() {
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
                  {this.renderCartItems()}
                </tbody>
              </Table>
          </div>
        )
    }
}


export default connect(state => ({
      cart:     state.services.cart,
}))(ManageCart);
