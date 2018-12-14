import React, { Component } from 'react';
import Products from  './Products';

const Landing = (props) =>  {
    return(
        <div>
          <Products
            productsList={props.productList}
            searchTerm={props.searchTerm}
            addToCart={props.addToCart}
            openModal={props.openModal}
          />
        </div>
    );
}

export default Landing;
