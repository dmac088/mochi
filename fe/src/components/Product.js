import React, {Component} from 'react';
import PropTypes from 'prop-types';
import Counter from './Counter';

class Product extends Component {

	constructor(props){
		super(props);
        this.state = {
            selectedProduct: {},
            quickViewProdcut: {},
            isAdded: false,
						quantity: 1,
        }
    }

		updateQuantity = (value) => {
			this.setState((prevState, value) => ({
				 quantity:  value
			}), () => {
					console.log('updateQuantity AFTER', this.state.quantity);
			});
		}

    resetQuantity = () => {
		 	this.setState({
		 		quantity: 1
		 	});
   	}

    addToCart = (image, name, price, id, quantity) => {
        this.setState({
            selectedProduct: {
                image: image,
                name: name,
                price: price,
                id: id,
                quantity: quantity
            }
        }, () => {
            					this.props.addToCart(this.state.selectedProduct);
        				 })

        this.setState({
            isAdded: true
        },

					() => {
            setTimeout(() => {
                this.setState({
                    isAdded: false,
                    selectedProduct: {}
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
        let image = this.props.image;
        let name = this.props.name;
        let price = this.props.price;
        let id = this.props.id;
        let quantity = this.state.quantity;
        return(
            <div className="product">
                <div className="product-image">
                    <img src={image} alt={this.props.name} onClick={this.quickView.bind(this, image, name, price, id, quantity)}/>
                </div>
                <h4 className="product-name">{this.props.name}</h4>
                <p className="product-price">{this.props.price}</p>
								<Counter updateQuantity={this.updateQuantity}
												 productQty={this.state.quantity}
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


Product.propTypes = {
  quantity: PropTypes.number
};

export default Product;
