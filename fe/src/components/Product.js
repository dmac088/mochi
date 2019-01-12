import React, {Component} from 'react';
import PropTypes from 'prop-types';
import Counter from './Counter';
import config from '../config/config';

class Product extends Component {

	constructor(props){
		super(props);
        this.state = {
												productDTO: {
													"productId": null,
													"productUPC": null,
													"productCreateDt": null,
													"productDesc": null,
													"productRrp": null,
													"lclCd": null,
													"productImage": null,
													"quantity": 0,
												},
	            					quickViewProdcut: {},
	            					isAdded: false,
        						 }
    }

		incrementQuantity = () => {
			this.setState((prevState, value) => ({
				 productDTO: {quantity: prevState.productDTO.quantity +1}
			}));
		}

		decrementQuantity = () => {
			this.setState((prevState, value) => ({
				 productDTO: {quantity: prevState.productDTO.quantity -1}
			}));
		}

    resetQuantity = () => {
		 	this.setState({
		 		quantity: 1
		 	});
   	}

    addToCart = (image, name, price, id, quantity) => {
        this.setState({
            productDTO: {
                image: config.url + '/' + image,
                name: name,
                price: price,
                id: id,
                quantity: quantity
            }
        }, () => {
            		 	this.props.addToCart(this.state.productDTO);
        				 })

        this.setState({
            isAdded: true
        },

					() => {
            setTimeout(() => {
                this.setState({
                    isAdded: false,
                    productDTO: {}
                });
            }, 3500);
        	}
			);
    }

    quickView = (image, name, price, id) => {
        this.setState({
            quickViewProdcut: {
                image: image,
                name: name,
                price: price,
                id: id
            }
        }, () => {
            			this.props.openModal(this.state.quickViewProdcut);
        				 })
    }


    render(){
        let image = config.url + '/' + this.props.product.productImage;
        let name = this.props.product.productDesc;
        let price = this.props.product.productRrp;
        let id = this.props.product.productId;
        let quantity = this.state.currentQuantity;
        return(
            <div className="product">
                <div className="product-image">
                    <img src={image} alt={this.props.name} onClick={this.quickView.bind(this, image, name, price, id, quantity)}/>
                </div>
                <h4 className="product-name">{name}</h4>
                <p className="product-price">{price}</p>
								<Counter incrementQuantity={this.incrementQuantity}
												 decrementQuantity={this.decrementQuantity}
												 productQty={quantity}
								/>
                <div className="product-action">
                    <button className={!this.state.isAdded ? "" : "added"}
														type="button"
														onClick={this.addToCart.bind(this, image, name, price, id, quantity)}>{
																!this.state.isAdded ?
																this.props.lang.addToCart :
																this.props.lang.addedToCart}
										</button>
                </div>
            </div>
        )
    }
}

export default Product;
