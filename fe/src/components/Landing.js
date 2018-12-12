import React, { Component } from 'react';
import Products from  './Products';

const Landing = (props) =>  {
    return(
        <div>
          <Products
            productsList={props.items}
            searchTerm={props.searchTerm}
            addToCart={props.addToCart}
            productQuantity=''
            updateQuantity=''
            openModal=''
          />
        </div>
    );
}

export default Landing;
