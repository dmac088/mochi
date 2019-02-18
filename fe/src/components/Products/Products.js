import React from 'react';
import Product from './Product';
import NoResults from "../empty-states/NoResults";
import ReactCSSTransitionGroup from 'react-addons-transition-group';


const Products = (props) => {

    	let productsData;

			productsData = props.productsList.map(product =>{

				return(
            <div class="shop-page-container mb-50">
              <div class="container">
                <div class="row">
                  <div class="col-lg-3 order-2 order-lg-1">
                    <!--=======  sidebar area  =======-->

                    <div class="sidebar-area">

                    </div>
                  </div>
                </div>
              </div>
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
