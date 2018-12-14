import React, {Component} from 'react';
import config from '../config/config';
import Product from './Product';
import LoadingProducts from '../loaders/Products';
import NoResults from "../empty-states/NoResults";
import CSSTransitionGroup from 'react-transition-group/CSSTransitionGroup';

class Products extends Component{
	constructor(){
		//console.log('Products...');
		super();
	}
  	render(){
    	let productsData;
    	let term = this.props.searchTerm;
    	let x;

			function searchingFor(term){
				return function(x){
					return x.productDesc.toLowerCase().includes(term.toLowerCase()) || !term;
				}
			}

			productsData = this.props.productsList.filter(searchingFor(term)).map(product =>{

				return(
							<Product key={product.productId}
									 price={product.productRrp}
									 name={product.productDesc}
									 image={config.url + '/' + product.productImage}
									 id={product.productId}
									 addToCart={this.props.addToCart}
									 openModal={this.props.openModal}
							/>
					)
				}
		);

		// Empty and Loading States
		let view;
		if(productsData.length <= 0 && !term){
			view = <LoadingProducts />
		} else if(productsData.length <= 0 && term){
			view = <NoResults />
		} else{
			view =
			<CSSTransitionGroup
				transitionName="fadeIn"
				transitionEnterTimeout={500}
				transitionLeaveTimeout={300}
				component="div"
				className="products">
					{productsData}
			</CSSTransitionGroup>
		}
		return(
			<div className="products-wrapper">
				{view}
			</div>
		)
	}
}

export default Products;
