import React from 'react';
import config from '../config/config';
import Product from './Product';
import LoadingProducts from '../loaders/Products';
import NoResults from "../empty-states/NoResults";
import CSSTransitionGroup from 'react-transition-group/CSSTransitionGroup';

function searchingFor(term){
	return function(x){
		return x.productDesc.toLowerCase().includes(term.toLowerCase()) || !term;
	}
}

const Products = (props) => {
    	let productsData;
    	let term = props.searchTerm;

			productsData = props.productsList.filter(searchingFor(term)).map(product =>{

				return(
							<Product key=				{product.productId}
											 price=			{product.productRrp}
											 name=			{product.productDesc}
											 image=			{config.url + '/' + product.productImage}
											 id=				{product.productId}
											 addToCart=	{props.addToCart}
											 openModal=	{props.openModal}
											 lang=			{props.lang}
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


export default Products;
