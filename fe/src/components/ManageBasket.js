import React, {Component} from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as tokensActionCreators from '../services/session/actions';
import * as customerActionCreators from '../services/customer/actions';
import * as cartActionCreators from '../services/cart/actions';
import * as cartService from '../services/cart';
import config from '../config/config';

class ManageBasket extends Component {

  constructor(props){
    //console.log('Products...');
    super(props);
  }

  removeItem = (event) => {
    console.log('removeItem');
    console.log(event.target.id);
    console.log(this.props.cart);
    //console.log(this.props.cart.items[1].id === Number(event.target.id));
    cartService.removeFromCart(this.props.cart, Number(event.target.id));

  }


  render() {
        console.log(this.props);
        let basketItems;

        basketItems = this.props.cart.items.map(product => {
          console.log(product);
            return(

              <tr>
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
                  <select className="form-control">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                  </select>
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
          }
      );


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
                  {basketItems}
                </tbody>
              </table>
            </div>
            </div>

        )
    }
}

export default connect(state => ({
  tokens:  state.services.session.tokens,
  customer: state.services.customer.customer,
  cart:  state.services.cart.cart,
}), dispatch => ({
	actions: {
		tokens: bindActionCreators(tokensActionCreators, dispatch),
    customer: bindActionCreators(customerActionCreators, dispatch),
    cart: bindActionCreators(cartActionCreators, dispatch),
	},
}))(ManageBasket);
