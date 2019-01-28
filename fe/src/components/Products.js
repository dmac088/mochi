import React from 'react';
import Product from './Product';
import NoResults from "../empty-states/NoResults";
import ReactCSSTransitionGroup from 'react-addons-transition-group';


const Products = (props) => {

    	let productsData;

			productsData = props.productsList.map(product =>{

				return(
					<div key={product.productId}>
							<Product
											 product=		{product}
											 openModal=	{props.openModal}
											 lang=			{props.lang}
							/>
					</div>
					)
				}
		);

		// Empty and Loading States
		let view;
		if(productsData.length <= 0){
			view = <NoResults />
		} else{
			view =
			<ReactCSSTransitionGroup
				transitionName="fadeIn"
				transitionEnterTimeout={500}
				transitionLeaveTimeout={300}
				component="div"
				className="products">
					{productsData}
			</ReactCSSTransitionGroup>
		}
		return(
			<div className="products-wrapper">
				{view}
			</div>
		)
	}


export default Products;
