import React, { Component } from 'react';
import Products from  './Products';

const Landing = (props) =>  {
    return(
        <div>
          <Products
            productsList={props.items}
            searchTerm=''
            addToCart=''
            productQuantity=''
            updateQuantity=''
            openModal=''
          />
        </div>
    );
}

export default Landing;
