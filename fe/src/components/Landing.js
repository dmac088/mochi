import React, { Component } from 'react';
import Products from  './Products';

const Landing = (props) =>  {
  //console.log(props);
    return(
        <div>
          <Products
            productsList={props.productList}
            searchTerm={props.searchTerm}
            addToCart={props.addToCart}
            productQuantity={props.productQuantity}
            updateQuantity={props.updateQuantity}
            openModal={props.openModal}
          />
        </div>
    );
}

export default Landing;
